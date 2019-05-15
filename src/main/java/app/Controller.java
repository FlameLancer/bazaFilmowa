package app;

import core.*;
import core.enumerator.Sex;
import customNode.FilteredComboBox;
import dao.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public enum ChoiceMovies {
        Tytul,
        Rezyser
    }

    public enum ChoiceCast {
        Imie,
        Nazwisko
    }

    //-----Movies Tab-----//
    @FXML
    private Label movieIdLabel;
    @FXML
    private TextField orgTitleTextBox;
    @FXML
    private TextField polishTitleTextBox;
    @FXML
    private FilteredComboBox<Cast> directorComboBox;
    @FXML
    private DatePicker premiereDatePicker;
    @FXML
    private FilteredComboBox<Languages> languageComboBox;
    @FXML
    private TextField lengthTextBox;
    @FXML
    private ListView<Cast> allActorsListView;
    @FXML
    private ListView<Cast> actorsListView;
    @FXML
    private ListView<Genres> allGenresListView;
    @FXML
    private ListView<Genres> genresListView;
    @FXML
    private TextArea movieAboutTextBox;
    @FXML
    private TableView<Movies> moviesTable;
    @FXML
    private TableColumn<Movies, Integer> idMoviesCol;
    @FXML
    private TableColumn<Movies, String> orgTitleCol;
    @FXML
    private TableColumn<Movies, String> polishTitleCol;
    @FXML
    private TableColumn<Movies, Cast>  directorCol;
    @FXML
    private TableColumn<Movies, Date> premiereCol;
    @FXML
    private TableColumn<Movies, Languages> movieLanguageCol;
    @FXML
    private TableColumn<Movies, Integer> lengthCol;
    @FXML
    private TableColumn<Movies, List<Genres>> movieGenresCol;
    @FXML
    private TableColumn<Movies, List<Cast>> actorsCol;
    @FXML
    private TableColumn<Movies, String> movieAboutCol;
    @FXML
    private Button addMovieButton;
    @FXML
    private Button modifyMovieButton;
    @FXML
    private Button removeMovieButton;
    @FXML
    private Button addActorsButton;
    @FXML
    private Button removeActorsButton;
    @FXML
    private Button removeGenresButton;
    @FXML
    private Button addGenresButton;
    @FXML
    private Button resetMovieButton;

    //Wyszukiwanie
    @FXML
    private Button searchMoviesButton;
    @FXML
    private ComboBox<ChoiceMovies> searchMoviesCombobox;


    //-----Cast Tab-----//
    @FXML
    private Label castIdLabel;
    @FXML
    private TextField nameTextBox;
    @FXML
    private TextField surnameTextBox;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private DatePicker deathDatePicker;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private FilteredComboBox<Countries> countryComboBox;
    @FXML
    private TextArea castAboutTextBox;
    @FXML
    private TableView<Cast> castTable;
    @FXML
    private TableColumn<Cast, Integer> idCastCol;
    @FXML
    private TableColumn<Cast, String> nameCol;
    @FXML
    private TableColumn<Cast, String> surnameCol;
    @FXML
    private TableColumn<Cast, Date> birthDateCol;
    @FXML
    private TableColumn<Cast, Date>  deathDateCol;
    @FXML
    private TableColumn<Cast, Sex> sexCol;
    @FXML
    private TableColumn<Cast, Countries> castCountryCol;
    @FXML
    private TableColumn<Cast, List<Movies>> castMoviesCol;
    @FXML
    private TableColumn<Cast, String> castAboutCol;
    @FXML
    private Button addCastButton;
    @FXML
    private Button modifyCastButton;
    @FXML
    private Button removeCastButton;
    @FXML
    private Button resetActorsButton;

    //Wyszukiwanie
    @FXML
    private Button searchCastButton;
    @FXML
    private ComboBox<ChoiceCast> searchCastCombobox;

    //-----Genres Tab-----//
    @FXML
    private Label genreIdLabel;
    @FXML
    private TextField genreTextBox;
    @FXML
    private TableView<Genres> genresTable;
    @FXML
    private TableColumn<Genres, Integer> idGenresCol;
    @FXML
    private TableColumn<Genres, String> genreCol;
    @FXML
    private Button addGenreButton;
    @FXML
    private Button modifyGenreButton;
    @FXML
    private Button removeGenreButton;

    //-----Languages Tab-----//
    @FXML
    private Label languageIdLabel;
    @FXML
    private TextField languageTextBox;
    @FXML
    private TableView<Languages> languagesTable;
    @FXML
    private TableColumn<Languages, Integer> idLanguagesCol;
    @FXML
    private TableColumn<Languages, String> languageCol;
    @FXML
    private Button addLanguageButton;
    @FXML
    private Button modifyLanguageButton;
    @FXML
    private Button removeLanguageButton;

    //-----Countries Tab-----//
    @FXML
    private Label countryIdLabel;
    @FXML
    private TextField countryTextBox;
    @FXML
    private TableView<Countries> countriesTable;
    @FXML
    private TableColumn<Countries, Integer> idCountriesCol;
    @FXML
    private TableColumn<Countries, String> countryCol;
    @FXML
    private Button addCountryButton;
    @FXML
    private Button modifyCountryButton;
    @FXML
    private Button removeCountryButton;

    @FXML
    private Pane searchMoviesPane;
    @FXML
    private Pane searchCastPane;

    private final MoviesDAO moviesDAO = new MoviesDAO();
    private final CastDAO castDAO = new CastDAO();
    private final GenresDAO genresDAO = new GenresDAO();
    private final LanguagesDAO languagesDAO = new LanguagesDAO();
    private final CountriesDAO countriesDAO = new CountriesDAO();





    //-----Init-----//
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showMovies();
        showCast();
        showGenres();
        showLanguages();
        showCountries();


        //-----Buttons-----//

        //-----Movies-----//
        //Dodawanie
        addMovieButton.setOnAction(event->{
            if(orgTitleTextBox.getText() != null && !orgTitleTextBox.getText().isEmpty()){
                Movies movie = new Movies();
                movie.setId(null);
                movie.setOrgTitle(orgTitleTextBox.getText());
                movie.setPolishTitle(polishTitleTextBox.getText());
                movie.setDirector(directorComboBox.getValue());
                movie.setPremiere(Date.valueOf(premiereDatePicker.getValue()));
                movie.setLanguage(languageComboBox.getValue());
                movie.setLength(Integer.parseInt(lengthTextBox.getText()));
                movie.setAbout(movieAboutTextBox.getText());

                movie.setActors( actorsListView.getItems());

                movie.setGenreList( genresListView.getItems());

                moviesDAO.saveOrUpdate(movie);
                moviesTable.setItems(FXCollections.observableList(moviesDAO.getAll()));
            }
            clearXd();
        });
        //Usuwanie
        removeMovieButton.setOnAction(event->{
            if(moviesTable.getSelectionModel().getSelectedItem() != null){
                Movies movie =moviesTable.getSelectionModel().getSelectedItem();

                moviesDAO.delete(movie);
                moviesTable.setItems(FXCollections.observableList(moviesDAO.getAll()));
                orgTitleTextBox.clear();
                polishTitleTextBox.clear();
                directorComboBox.setSelectedItem(null);
                directorComboBox.setValue(null);
                premiereDatePicker.setValue(null);
                languageComboBox.setSelectedItem(null);
                languageComboBox.setValue(null);
                lengthTextBox.clear();
                movieAboutTextBox.clear();
                actorsListView.setItems(FXCollections.observableArrayList());
                genresListView.setItems(FXCollections.observableArrayList());

            }
            clearXd();
        });
        //Modyfikacja
        modifyMovieButton.setOnAction(event->{
            if(orgTitleTextBox.getText() != null && !orgTitleTextBox.getText().isEmpty()) {
                Movies movie = moviesTable.getSelectionModel().getSelectedItem();
                movie.setOrgTitle(orgTitleTextBox.getText());
                movie.setPolishTitle(polishTitleTextBox.getText());
                movie.setDirector(directorComboBox.getValue());
                movie.setPremiere(Date.valueOf(premiereDatePicker.getValue()));
                movie.setLanguage(languageComboBox.getValue());
                movie.setLength(Integer.parseInt(lengthTextBox.getText()));
                movie.setAbout(movieAboutTextBox.getText());

                List<Cast> actorsList = actorsListView.getItems();
                movie.setActors( actorsList );

                List<Genres> genresList = genresListView.getItems();
                movie.setGenreList( genresList );

                moviesDAO.saveOrUpdate(movie);
                moviesTable.setItems(FXCollections.observableList(moviesDAO.getAll()));

            }
            clearXd();
        });
        //Wyczyszczenie Formularza
        resetMovieButton.setOnAction(event -> {
            clearXd();
        });
        //Wyszukaj
        searchCastButton.setOnAction(event -> {
            ChoiceCast wyb = searchCastCombobox.getValue();
            switch(wyb){
                case Imie:
                    //TODO
                    TextField tf = (TextField) searchCastPane.getChildren().get(0);
                    castTable.setItems(FXCollections.observableArrayList(new CastDAO().getByName(tf.getText())));
                    break;
                case Nazwisko:
                    //TODO
                    TextField tf2 = (TextField) searchCastPane.getChildren().get(0);
                    castTable.setItems(FXCollections.observableArrayList(new CastDAO().getBySurname(tf2.getText())));
                    break;
                default:
                    return;
            }


        });

        //-----Cast-----//
        //Dodawanie
        addCastButton.setOnAction(event->{
            if(nameTextBox.getText() != null && !nameTextBox.getText().isEmpty() &&
                    surnameTextBox.getText() != null && !surnameTextBox.getText().isEmpty()) {
                Cast cast = new Cast();
                cast.setId(null);
                cast.setName(nameTextBox.getText());
                cast.setSurname(surnameTextBox.getText());
                cast.setBirthDate((birthDatePicker.getValue() == null) ? null : Date.valueOf(birthDatePicker.getValue()));
                cast.setDeathDate((deathDatePicker.getValue() == null) ? null : Date.valueOf(deathDatePicker.getValue()));
                cast.setSex(maleRadioButton.isSelected() ? Sex.Mezczyzna : Sex.Kobieta);
                cast.setCountry(countryComboBox.getValue());
                cast.setAbout(castAboutTextBox.getText());

                castDAO.saveOrUpdate(cast);
                castTable.setItems(FXCollections.observableList(castDAO.getAll()));

            }
            clearXd();
        });
        //Usuwanie
        removeCastButton.setOnAction(event->{
            if(castTable.getSelectionModel().getSelectedItem() != null){
                Cast cast = castTable.getSelectionModel().getSelectedItem();

                castDAO.delete(cast);
                castTable.setItems(FXCollections.observableList(castDAO.getAll()));
                nameTextBox.clear();
                surnameTextBox.clear();
                birthDatePicker.setValue(null);
                deathDatePicker.setValue(null);
                maleRadioButton.setSelected(true);
                femaleRadioButton.setSelected(false);
                countryComboBox.setSelectedItem(null);
                countryComboBox.setValue(null);
                castAboutTextBox.clear();

            }
            clearXd();
        });
        //Modyfikacja
        modifyCastButton.setOnAction(event->{
            if(nameTextBox.getText() != null && !nameTextBox.getText().isEmpty() &&
                    surnameTextBox.getText() != null && !surnameTextBox.getText().isEmpty()) {

                Cast cast = castTable.getSelectionModel().getSelectedItem();
                cast.setName(nameTextBox.getText());
                cast.setSurname(surnameTextBox.getText());
                cast.setBirthDate((birthDatePicker.getValue() == null) ? null : Date.valueOf(birthDatePicker.getValue()));
                cast.setDeathDate((deathDatePicker.getValue() == null) ? null : Date.valueOf(deathDatePicker.getValue()));
                cast.setSex(maleRadioButton.isSelected() ? Sex.Mezczyzna : Sex.Kobieta);
                cast.setCountry(countryComboBox.getValue());
                cast.setAbout(castAboutTextBox.getText());

                castDAO.saveOrUpdate(cast);
                castTable.setItems(FXCollections.observableList(castDAO.getAll()));

            }
            clearXd();
        });
        //Wyczyszczenie Formularza
        resetActorsButton.setOnAction(event -> {
            clearXd();
        });
        //Wyszukaj
        searchMoviesButton.setOnAction(event -> {
            ChoiceMovies wyb = searchMoviesCombobox.getValue();
            switch(wyb){
                case Tytul:
                    //TODO
                    TextField tf = (TextField) searchMoviesPane.getChildren().get(0);
                    moviesTable.setItems(FXCollections.observableArrayList(new MoviesDAO().getByOrgTitle(tf.getText())));
                    break;
                case Rezyser:
                    //TODO
                    FilteredComboBox<Cast> fcb = (FilteredComboBox<Cast>) searchMoviesPane.getChildren().get(0);
                    moviesTable.setItems(FXCollections.observableArrayList(new MoviesDAO().getByDirector(fcb.getSelectedItem())));
                    break;
                default:
                    return;
            }


        });

        //-----Genres-----//
        //Dodawanie
        addGenreButton.setOnAction(event->{
            if(genreTextBox.getText() != null && !genreTextBox.getText().isEmpty()) {
                Genres genres = new Genres();
                genres.setId(null);
                genres.setName(genreTextBox.getText());

                genresDAO.saveOrUpdate(genres);
                genresTable.setItems(FXCollections.observableList(genresDAO.getAll()));
            }
            clearXd();
        });
        //Usuwanie
        removeGenreButton.setOnAction(event->{
            if(genresTable.getSelectionModel().getSelectedItem() != null){
                Genres genres = genresTable.getSelectionModel().getSelectedItem();
                genresDAO.delete(genres);
                genresTable.setItems(FXCollections.observableList(genresDAO.getAll()));
                genreTextBox.clear();
            }
            clearXd();
        });
        //Modyfikacja
        modifyGenreButton.setOnAction(event->{
            if(genreTextBox.getText() != null && !genreTextBox.getText().isEmpty()) {
                Genres genres = genresTable.getSelectionModel().getSelectedItem();
                genres.setName(genreTextBox.getText());

                genresDAO.saveOrUpdate(genres);
                genresTable.setItems(FXCollections.observableList(genresDAO.getAll()));
            }
            clearXd();
        });
        //-----Languages-----//
        //Dodawanie
        addLanguageButton.setOnAction(event->{
            if(languageTextBox.getText() != null && !languageTextBox.getText().isEmpty()) {
                Languages languages = new Languages();
                languages.setId(null);
                languages.setName(languageTextBox.getText());
                languagesDAO.saveOrUpdate(languages);
                languagesTable.setItems(FXCollections.observableList(languagesDAO.getAll()));
            }
            clearXd();
        });
        //Usuwanie
        removeLanguageButton.setOnAction(event->{
            if(languagesTable.getSelectionModel().getSelectedItem() != null){
                Languages languages = languagesTable.getSelectionModel().getSelectedItem();
                languagesDAO.delete(languages);
                languagesTable.setItems(FXCollections.observableList(languagesDAO.getAll()));
                languageTextBox.clear();
            }
            clearXd();
        });
        //Modyfikacja
        modifyLanguageButton.setOnAction(event->{
            if(languageTextBox.getText() != null && !languageTextBox.getText().isEmpty()) {
                Languages languages = languagesTable.getSelectionModel().getSelectedItem();
                languages.setName(languageTextBox.getText());
                languagesDAO.saveOrUpdate(languages);
                languagesTable.setItems(FXCollections.observableList(languagesDAO.getAll()));
            }
            clearXd();
        });
        //-----Countries-----//
        //Dodawanie
        addCountryButton.setOnAction(event->{
            if(countryTextBox.getText() != null && !countryTextBox.getText().isEmpty()) {
                Countries countries = new Countries();
                countries.setId(null);
                countries.setName(countryTextBox.getText());
                countriesDAO.saveOrUpdate(countries);
                countriesTable.setItems(FXCollections.observableList(countriesDAO.getAll()));
            }
            clearXd();
        });
        //Usuwanie
        removeCountryButton.setOnAction(event->{
            if(countriesTable.getSelectionModel().getSelectedItem() != null){
                Countries countries = countriesTable.getSelectionModel().getSelectedItem();
                countriesDAO.delete(countries);
                countriesTable.setItems(FXCollections.observableList(countriesDAO.getAll()));
                countryTextBox.clear();
            }
            clearXd();
        });
        //Modyfikacja
        modifyCountryButton.setOnAction(event->{
            if(countryTextBox.getText() != null && !countryTextBox.getText().isEmpty()) {
                Countries countries = countriesTable.getSelectionModel().getSelectedItem();
                countries.setName(countryTextBox.getText());
                countriesDAO.saveOrUpdate(countries);
                countriesTable.setItems(FXCollections.observableList(countriesDAO.getAll()));
            }
            clearXd();
        });



        //-----Fill Everything-----//
        moviesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldV, newV)->{
            if(newV == null){
                clearXd();
                return;
            }
            orgTitleTextBox.setText(newV.getOrgTitle());
            polishTitleTextBox.setText(newV.getPolishTitle());

            List<Cast> castList = new CastDAO().getAll();
            Cast remThisCast = new Cast();
            for(Cast caste : castList){
                if(caste.getId().equals(newV.getId())){
                    remThisCast = caste;
                    break;
                }
            }
            castList.remove(remThisCast);
            directorComboBox.set(castList);

            directorComboBox.getSelectionModel().select(newV.getDirector());
            directorComboBox.setValue(newV.getDirector());

            premiereDatePicker.setValue((newV.getPremiere() == null) ? null : newV.getPremiere().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            List<Languages> languages = new LanguagesDAO().getAll();
            Languages remThisLang = new Languages();
            for(Languages lang : languages){
                if(lang.getId().equals(newV.getId())){
                    remThisLang = lang;
                    break;
                }
            }
            languages.remove(remThisLang);
            languageComboBox.set(languages);

            languageComboBox.getSelectionModel().select(newV.getLanguage());
            languageComboBox.setValue(newV.getLanguage());
            lengthTextBox.setText(newV.getLength().toString());
            movieAboutTextBox.setText(newV.getAbout());

            actorsListView.setItems(FXCollections.observableArrayList(newV.getActors()));
            genresListView.setItems(FXCollections.observableArrayList(newV.getGenreList()));


            List<Cast> actors = new CastDAO().getAll();
            List<Cast> xd = new ArrayList<>();
            for(Cast c : newV.getActors()){
                for(Cast c2 : actors)
                    if(c.getId().equals(c2.getId()))
                        xd.add(c2);
            }
            actors.removeAll(xd);
            List<Genres> genres = new GenresDAO().getAll();
            List<Genres> xd2 = new ArrayList<>();
            for(Genres c : newV.getGenreList()){
                for(Genres c2 : genres)
                    if(c.getId().equals(c2.getId()))
                        xd2.add(c2);
            }
            genres.removeAll(xd2);

            allActorsListView.setItems(FXCollections.observableArrayList(actors));
            allGenresListView.setItems(FXCollections.observableArrayList(genres));


        });

        castTable.getSelectionModel().selectedItemProperty().addListener((obs,oldV, newV)->{
            if(newV == null)
                return;
           castIdLabel.setText(newV.getId().toString());
           nameTextBox.setText(newV.getName());
           surnameTextBox.setText(newV.getSurname());
           birthDatePicker.setValue((newV.getBirthDate() == null) ? null : newV.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
           deathDatePicker.setValue((newV.getDeathDate() == null) ? null : newV.getDeathDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
           maleRadioButton.setSelected(newV.getSex().toString().equals("male"));
           femaleRadioButton.setSelected(newV.getSex().toString().equals("female"));
           countryComboBox.getSelectionModel().select(newV.getCountry());
           countryComboBox.setValue(newV.getCountry());
           castAboutTextBox.setText(newV.getAbout());


        });

        genresTable.getSelectionModel().selectedItemProperty().addListener((obs,oldV, newV)->{
            if(newV == null)
                return;
            genreIdLabel.setText(newV.getId().toString());
            genreTextBox.setText(newV.getName());

        });

        languagesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldV, newV)->{
            if(newV == null)
                return;
            genreIdLabel.setText(newV.getId().toString());
            languageTextBox.setText(newV.getName());

        });

        countriesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldV, newV)->{
            if(newV == null)
                return;
            countryIdLabel.setText(newV.getId().toString());
            countryTextBox.setText(newV.getName());

        });

        //

        addActorsButton.setOnAction(event -> {
            Cast c = allActorsListView.getSelectionModel().getSelectedItem();
            if (c == null)
                return;
            allActorsListView.getItems().remove(c);
            actorsListView.getItems().add(c);
        });

        removeActorsButton.setOnAction(event -> {
            Cast c = actorsListView.getSelectionModel().getSelectedItem();
            if (c == null)
                return;
            actorsListView.getItems().remove(c);
            allActorsListView.getItems().add(c);
        });

        addGenresButton.setOnAction(event -> {
            Genres g = allGenresListView.getSelectionModel().getSelectedItem();
            if (g == null)
                return;
            allGenresListView.getItems().remove(g);
            genresListView.getItems().add(g);
        });

        removeGenresButton.setOnAction(event -> {
            Genres g = genresListView.getSelectionModel().getSelectedItem();
            if (g == null)
                return;
            genresListView.getItems().remove(g);
            allGenresListView.getItems().add(g);
        });

        searchMoviesCombobox.getSelectionModel().selectedItemProperty().addListener((obs, oV, nV)->{
            searchMoviesPane.getChildren().removeAll(searchMoviesPane.getChildren());
            switch(nV){
                case Tytul:
                    //TODO
                    TextField tf = new TextField();
                    tf.setPrefWidth(180);
                    searchMoviesPane.getChildren().add(tf);
                    break;
                case Rezyser:
                    //TODO
                    FilteredComboBox<Cast> fcb = new FilteredComboBox<>();
                    fcb.set(new CastDAO().getAll());
                    fcb.setPrefWidth(180);
                    searchMoviesPane.getChildren().add(fcb);
                    break;
                default:
                    return;
            }
        });

        searchCastCombobox.getSelectionModel().selectedItemProperty().addListener((obs, oV, nV)->{
            searchCastPane.getChildren().removeAll(searchCastPane.getChildren());
            switch(nV){
                case Imie:
                    //TODO
                    TextField tf = new TextField();
                    tf.setPrefWidth(180);
                    searchCastPane.getChildren().add(tf);
                    break;
                case Nazwisko:
                    //TODO
                    TextField tf2 = new TextField();
                    tf2.setPrefWidth(180);
                    searchCastPane.getChildren().add(tf2);
                    break;
                default:
                    return;
            }
        });



        searchMoviesCombobox.setItems(FXCollections.observableArrayList(ChoiceMovies.values()));
        searchMoviesCombobox.getSelectionModel().select(searchMoviesCombobox.getItems().get(0));

        searchCastCombobox.setItems(FXCollections.observableArrayList(ChoiceCast.values()));
        searchCastCombobox.getSelectionModel().select(searchCastCombobox.getItems().get(0));

        clearXd();
    }

    //

    private void clearXd(){
        moviesTable.getSelectionModel().select(null);
        orgTitleTextBox.clear();
        polishTitleTextBox.clear();
        directorComboBox.set(new CastDAO().getAll());
        directorComboBox.setSelectedItem(null);
        directorComboBox.setValue(null);
        premiereDatePicker.setValue(null);
        languageComboBox.set(new LanguagesDAO().getAll());
        languageComboBox.setSelectedItem(null);
        languageComboBox.setValue(null);
        lengthTextBox.clear();
        movieAboutTextBox.clear();
        actorsListView.setItems(FXCollections.observableArrayList());
        genresListView.setItems(FXCollections.observableArrayList());

        allActorsListView.setItems(FXCollections.observableArrayList(new CastDAO().getAll()));
        allGenresListView.setItems(FXCollections.observableArrayList(new GenresDAO().getAll()));

        castTable.getSelectionModel().select(null);
        countryComboBox.set(new CountriesDAO().getAll());
        nameTextBox.clear();
        surnameTextBox.clear();
        birthDatePicker.setValue(null);
        deathDatePicker.setValue(null);
        maleRadioButton.setSelected(true);
        femaleRadioButton.setSelected(false);
        countryComboBox.setSelectedItem(null);
        countryComboBox.setValue(null);
        castAboutTextBox.clear();

        genreTextBox.clear();
        countryTextBox.clear();
        languageTextBox.clear();
    }


    //-----Show-----//

    private void showMovies () {
        idMoviesCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(moviesTable.getItems().indexOf(column.getValue())+1));
        orgTitleCol.setCellValueFactory(new PropertyValueFactory<>("orgTitle"));
        polishTitleCol.setCellValueFactory(new PropertyValueFactory<>("polishTitle"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        premiereCol.setCellValueFactory(new PropertyValueFactory<>("premiere"));
        movieLanguageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        movieGenresCol.setCellValueFactory(new PropertyValueFactory<>("genreList"));
        actorsCol.setCellValueFactory(new PropertyValueFactory<>("actors"));
        movieAboutCol.setCellValueFactory(new PropertyValueFactory<>("about"));
        moviesTable.setItems(FXCollections.observableArrayList(new MoviesDAO().getAll()));


    }

    private void showCast () {
        idCastCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(castTable.getItems().indexOf(column.getValue())+1));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        deathDateCol.setCellValueFactory(new PropertyValueFactory<>("deathDate"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        castCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        castMoviesCol.setCellValueFactory(new PropertyValueFactory<>("movies"));
        castAboutCol.setCellValueFactory(new PropertyValueFactory<>("about"));
        castTable.setItems(FXCollections.observableArrayList(new CastDAO().getAll()));
    }


    private void showGenres () {
        idGenresCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(genresTable.getItems().indexOf(column.getValue())+1));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        genresTable.setItems(FXCollections.observableArrayList(new GenresDAO().getAll()));
    }

    private void showLanguages () {
        idLanguagesCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(languagesTable.getItems().indexOf(column.getValue())+1));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        languagesTable.setItems(FXCollections.observableArrayList(new LanguagesDAO().getAll()));
    }

    private void showCountries () {
        idCountriesCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(countriesTable.getItems().indexOf(column.getValue())+1));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countriesTable.setItems(FXCollections.observableArrayList(new CountriesDAO().getAll()));
    }
}
