import core.enumerator.Sex;
import dao.*;
import hibernate.HibernateUtil;
import core.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        Scene scene = new Scene(root, 1050, 750);
        primaryStage.setTitle("Baza danych");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args){

        HibernateUtil.OpenConnection("/hibernate.cfg.xml");

        Date date = new Date(1546988400000L);
        Genres genre1 = new Genres(null,"Fantasy",null);
        Genres genre2 = new Genres(null,"Przygodowy",null);
        Languages lang = new Languages(null,"angielski");
        Countries country1 = new Countries(null,"Nowa Zelandia");
        Countries country2 = new Countries(null,"Wielka Brytania");
        Cast cast1 = new Cast(null,"Peter","Jackson",date,null, Sex.Mezczyzna,country1,null,"about");
        Cast cast2 = new Cast(null,"Martin","Freeman",date,null, Sex.Mezczyzna,country2,null,"about");
        Cast cast3 = new Cast(null,"Ian","McKellen",date,null, Sex.Mezczyzna,country2,null,"about");
        List<Genres> genresList = new ArrayList<>();
        genresList.add(genre1);
        genresList.add(genre2);
        List<Cast> castList = new ArrayList<>();
        castList.add(cast2);
        castList.add(cast3);
        Movies movie = new Movies(null,"Hobbit",null,cast1,date,lang,120,genresList,castList,"about");

        GenresDAO genresDAO = new GenresDAO();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        CountriesDAO countriesDAO = new CountriesDAO();
        CastDAO castDAO = new CastDAO();
        MoviesDAO moviesDAO = new MoviesDAO();

        genresDAO.saveOrUpdate(genre1);
        genresDAO.saveOrUpdate(genre2);
        languagesDAO.saveOrUpdate(lang);
        countriesDAO.saveOrUpdate(country1);
        countriesDAO.saveOrUpdate(country2);
        castDAO.saveOrUpdate(cast1);
        castDAO.saveOrUpdate(cast2);
        castDAO.saveOrUpdate(cast3);
        moviesDAO.saveOrUpdate(movie);

        launch(args);
        HibernateUtil.CloseConnection();
    }
    
}