package basesyntax.model.figure;

import javax.persistence.Entity;

@Entity
public class Triangle extends Figure {
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Triangle {id = " + getId() + ", color = " + getColor() + ", area = " + area + '}';
    }
}
