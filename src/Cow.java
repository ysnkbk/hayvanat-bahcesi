class Cow extends Animal {
    public Cow(boolean gender) {
        super(gender);
        this.steps = 2;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
