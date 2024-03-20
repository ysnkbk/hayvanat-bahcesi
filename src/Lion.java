class Lion extends Animal {
    public Lion(boolean gender) {
        super(gender);
        this.steps = 4;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
