class Rooster extends Animal {
    public Rooster(boolean gender) {
        super(gender);
        this.steps = 1;
    }

    @Override
    void move() {
        moveRandomly();
    }
}
