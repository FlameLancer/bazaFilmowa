package customNode;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class FilteredComboBox<T> extends ComboBox<T> {

    private boolean initialized = false;
    @Setter
    @Getter
    private T selectedItem;
    private FilteredList<T> fList;

    public void addD(T item){
        List<T> aL = new ArrayList<>(this.getItems());
        aL.add(item);
        this.set(aL);
    }

    public void remD(T item){
        List<T> aL = new ArrayList<>(this.getItems());
        aL.remove(item);
        this.set(aL);
    }

    private void setContent(List<T> items){
        ObservableList<T> oList = FXCollections.observableArrayList(items);

        fList = new FilteredList<T>(oList, s -> true);

        this.setItems(fList);
    }

    public void set(List<T> items) {

        setContent(items);

        if(initialized)
            return;

        this.addEventHandler(KeyEvent.KEY_PRESSED, event ->
        {
            if(!this.isShowing() || event.isShiftDown()) return;

            this.setEditable(true);
            this.getEditor().clear();
        });

        this.showingProperty().addListener((observable, oldValue, newValue) ->{

            if(newValue){
                selectedItem = null;

            } else{
                T value = this.getValue();
                if( value != null)
                    selectedItem = value;

                this.setEditable(false);

                this.getSelectionModel().select(selectedItem);
                this.setValue(selectedItem);
            }
        });

        this.setOnHidden(event -> fList.setPredicate(item -> true));

        this.getEditor().textProperty().addListener((obs, oldValue, newValue)->{
            if(!this.isShowing()) return;

            if(this.getSelectionModel().getSelectedItem() == null){
                fList.setPredicate(item -> {
                    if(item.toString().toLowerCase().contains(newValue.toLowerCase()))
                        return true;
                    return false;
                });
            }
        });

        ComboBoxListViewSkin<T> comboBoxListViewSkin = new ComboBoxListViewSkin<T>(this);
        comboBoxListViewSkin.getPopupContent().addEventFilter(KeyEvent.ANY, (event) -> {
            if( event.getCode() == KeyCode.SPACE ) {
                event.consume();
            }
        });
        this.setSkin(comboBoxListViewSkin);


        initialized = true;
    }

}
