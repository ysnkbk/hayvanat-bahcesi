import java.util.Random;

abstract class Animal {
    protected int x;
    protected int y;
    protected int steps;
    protected boolean gender;

    public Animal(boolean gender) {
        this.x = new Random().nextInt(AnimalSimulation.FIELD_WIDTH);
        this.y = new Random().nextInt(AnimalSimulation.FIELD_HEIGHT);
        this.steps = 1;
        this.gender = gender;
    }

    abstract void move();

    protected void moveRandomly() {
        x += new Random().nextInt(steps * 2 + 1) - steps;
        y += new Random().nextInt(steps * 2 + 1) - steps;
        x = Math.max(0, Math.min(x, AnimalSimulation.FIELD_WIDTH - 1));
        y = Math.max(0, Math.min(y, AnimalSimulation.FIELD_HEIGHT - 1));
    }
}
