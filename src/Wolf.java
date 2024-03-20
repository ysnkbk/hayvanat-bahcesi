class Wolf extends Animal {
    public Wolf(boolean gender) {
        super(gender);
        this.steps = 3;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
