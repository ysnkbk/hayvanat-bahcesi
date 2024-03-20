class Sheep extends Animal {
    public Sheep(boolean gender) {
        super(gender);
        this.steps = 2;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
