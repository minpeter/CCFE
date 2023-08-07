public class web_app3 {
    int x, y;

    web_app3(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void moveXY(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public static void main(String[] args) {
        web_app3 p = new web_app3(0, 0);
        System.out.println("x = " + p.x + ", y = " + p.y);
        p.setX(200);
        p.setY(300);
        System.out.println("x = " + p.x + ", y = " + p.y);
        p.moveXY(50, 50);
        System.out.println("x = " + p.x + ", y = " + p.y);
    }

}
