class Chicken extends Animal {
    public Chicken(boolean gender) {
        super(gender);
        this.steps = 1;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
