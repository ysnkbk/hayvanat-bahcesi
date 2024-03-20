class Hunter extends Animal {
    public Hunter(boolean gender) {
        super(gender);
        this.steps = 1;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
