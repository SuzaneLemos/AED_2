public class Ponto {

    double x;
    double y;
    int id;
    int nextID;

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        id = 1;
        nextID = id + 1;
    }

    public Ponto() {
        x = 0;
        y = 0;
        id = 1;
        nextID = id + 1;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double Y) {
        this.y = Y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getID() {
        return id;
    }

    public static int getNextID() {
        return nextID;
    }

    public double dist(Ponto second) {
        double distance;

        double distanceX = second.getX() - x;
        double distanceY = second.getY() - y;

        if (distanceX < 0) {
            distanceX *= -1;
        }
        if (distanceY < 0) {
            distanceY *= -1;
        }

        distance = distanceX + distanceY;

        return distance;
    }

    public double dist(int x, int y) {
        double distance;

        double distanceX = x - this.x;
        double distanceY = y - this.y;

        if (distanceX < 0) {
            distanceX *= -1;
        }
        if (distanceY < 0) {
            distanceY *= -1;
        }

        distance = distanceX + distanceY;

        return distance;
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        double distance;

        double distanceX = x1 - x2;
        double distanceY = y1 - y2;

        if (distanceX < 0) {
            distanceX *= -1;
        }
        if (distanceY < 0) {
            distanceY *= -1;
        }

        distance = distanceX + distanceY;

        return distance;
    }

    public static boolean isTriangulo(Ponto first, Ponto second, Ponto third) {
        boolean result = false;

        if ((first.getX() != second.getX() && first.getX() != third.getX() && second.getX() != third.getX())
                && (first.getY() != second.getY() && first.getY() != third.getY() && second.getY() != third.getY())) {
            result = true;
        }

        return result;
    }

    public double getAreaRetangulo(Ponto second) {
        double area;

        double distanceX = second.getX() - x;
        double distanceY = second.getY() - y;

        if (distanceX < 0) {
            distanceX *= -1;
        }
        if (distanceY < 0) {
            distanceY *= -1;
        }

        area = distanceX + distanceY;

        return area;
    }
}
