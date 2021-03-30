package basesyntax;

import basesyntax.dao.figure.FigureDao;
import basesyntax.dao.figure.FigureDaoImpl;
import basesyntax.dao.ma.PersonDao;
import basesyntax.dao.ma.PersonDaoImpl;
import basesyntax.model.figure.Circle;
import basesyntax.model.figure.Figure;
import basesyntax.model.figure.Triangle;
import basesyntax.model.ma.Coach;
import basesyntax.model.ma.Mentor;
import basesyntax.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(33);
        Triangle triangle = new Triangle();
        triangle.setArea(46);
        triangle.setColor("black");

        FigureDao<Figure> figureDao = new FigureDaoImpl(HibernateUtil.getSessionFactory());
        figureDao.save(circle);
        figureDao.save(triangle);
        figureDao.findById(1L, Figure.class).forEach(System.out::println);

        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("Bob");
        coach.setExperience(4);
        coach.setTrack(Coach.Track.JAVA);

        Mentor mentor = new Mentor();
        mentor.setAge(30);
        mentor.setName("Peter");

        PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());
    }
}
