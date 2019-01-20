package jp.ac.uryukyu.ie.e185761;

class mazeCoord {
    int x;
    int y;

    mazeCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;

        mazeCoord coord = (mazeCoord) o;
        return x == coord.x && y == coord.y;
    }

}
