import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalSimulation {

    public static final int FIELD_WIDTH = 500;
    public static final int FIELD_HEIGHT = 500;
    public static final int MOVE_COUNT = 1000;

    public List<Animal> animals;

    public AnimalSimulation() {
        animals = new ArrayList<>();
        initializeAnimals();
    }

    public static int getProximity(Animal animal1, Animal animal2) {
        double dx = animal1.x - animal2.x;
        double dy = animal1.y - animal2.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        new AnimalSimulation().start();
    }

    private void initializeAnimals() {
        addAnimals(Sheep.class, 15, true);
        addAnimals(Sheep.class, 15, false);
        addAnimals(Cow.class, 5, true);
        addAnimals(Cow.class, 5, false);
        addAnimals(Chicken.class, 10, false);
        addAnimals(Rooster.class, 10, true);
        addAnimals(Wolf.class, 5, true);
        addAnimals(Wolf.class, 5, false);
        addAnimals(Lion.class, 4, true);
        addAnimals(Lion.class, 4, false);
        addAnimals(Hunter.class, 1, true); // Avcının cinsiyeti belirtilmedi, erkek kabul edildi
    }

    private void addAnimals(Class<? extends Animal> animalClass, int count, boolean gender) {
        try {
            for (int i = 0; i < count; i++) {
                animals.add(animalClass.getConstructor(boolean.class).newInstance(gender));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        for (int i = 0; i < MOVE_COUNT; i++) {
            animals.forEach(Animal::move);
            hunt();
            reproduce();
            printCounts();
        }
        printStats();
    }

    private void printCounts() {
        int sheepCount = 0;
        int cowCount = 0;
        int chickenCount = 0;
        int roosterCount = 0;
        int wolfCount = 0;
        int lionCount = 0;
        int hunterCount = 0;

        for (Animal animal : animals) {
            if (animal instanceof Sheep) {
                sheepCount++;
            } else if (animal instanceof Cow) {
                cowCount++;
            } else if (animal instanceof Chicken) {
                chickenCount++;
            } else if (animal instanceof Rooster) {
                roosterCount++;
            } else if (animal instanceof Wolf) {
                wolfCount++;
            } else if (animal instanceof Lion) {
                lionCount++;
            } else if (animal instanceof Hunter) {
                hunterCount++;
            }
        }

        System.out.println("Sheep Count: " + sheepCount);
        System.out.println("Cow Count: " + cowCount);
        System.out.println("Chicken Count: " + chickenCount);
        System.out.println("Rooster Count: " + roosterCount);
        System.out.println("Wolf Count: " + wolfCount);
        System.out.println("Lion Count: " + lionCount);
        System.out.println("Hunter Count: " + hunterCount);
    }

    private void hunt() {
        for (Wolf wolf : getAnimals(Wolf.class)) {
            for (Animal prey : getNearbyAnimals(wolf, 4)) {
                if (prey instanceof Sheep || prey instanceof Chicken || prey instanceof Rooster) {
                    animals.remove(prey);
                    break;
                }
            }
        }

        for (Lion lion : getAnimals(Lion.class)) {
            for (Animal prey : getNearbyAnimals(lion, 5)) {
                if (prey instanceof Cow || prey instanceof Sheep) {
                    animals.remove(prey);
                    break;
                }
            }
        }

        for (Hunter hunter : getAnimals(Hunter.class)) {
            for (Animal prey : getNearbyAnimals(hunter, 8)) {
                if (prey instanceof Animal && prey != hunter) {
                    if (Math.random() < 1) {
                        animals.remove(prey);
                        break;
                    }
                }
            }
        }
    }

    private void reproduce() {
        List<Animal> newborns = new ArrayList<>();

        for (int i = 0; i < animals.size(); i++) {
            for (int j = i + 1; j < animals.size(); j++) {
                Animal animal1 = animals.get(i);
                Animal animal2 = animals.get(j);

                if (animal1.getClass() == animal2.getClass() && animal1.gender != animal2.gender &&
                        getProximity(animal1, animal2) <= 3) {
                    if (Math.random() < 0.2) {
                        boolean gender = new Random().nextBoolean();
                        Class<? extends Animal> clazz = animal1
                                .getClass();
                        try {
                            newborns.add(clazz.getConstructor(boolean.class).newInstance(gender));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        animals.addAll(newborns);
    }

    private List<Animal> getNearbyAnimals(Animal animal, int distance) {
        List<Animal> nearbyAnimals = new ArrayList<>();

        for (Animal otherAnimal : animals) {
            if (otherAnimal != animal && getProximity(animal, otherAnimal) <= distance) {
                nearbyAnimals.add(otherAnimal);
            }
        }

        return nearbyAnimals;
    }

    private <T extends Animal> List<T> getAnimals(Class<T> clazz) {
        List<T> animalList = new ArrayList<>();

        for (Animal animal : animals) {
            if (clazz.isAssignableFrom(animal.getClass())) {
                animalList.add(clazz.cast(animal));
            }
        }

        return animalList;
    }

    private void printStats() {
        int sheepCount = 0;
        int cowCount = 0;
        int chickenCount = 0;
        int roosterCount = 0;
        int wolfCount = 0;
        int lionCount = 0;
        int hunterCount = 0;

        for (Animal animal : animals) {
            if (animal instanceof Sheep) {
                sheepCount++;
            } else if (animal instanceof Cow) {
                cowCount++;
            } else if (animal instanceof Chicken) {
                chickenCount++;
            } else if (animal instanceof Rooster) {
                roosterCount++;
            } else if (animal instanceof Wolf) {
                wolfCount++;
            } else if (animal instanceof Lion) {
                lionCount++;
            } else if (animal instanceof Hunter) {
                hunterCount++;
            }
        }

        int totalPoints = sheepCount
                * 2 + cowCount * 2 + chickenCount + roosterCount + wolfCount * 3 + lionCount * 4 + hunterCount * 5;

        System.out.println("Total Points: " + totalPoints);
        System.out.println("Sheep Count: " + sheepCount);
        System.out.println("Cow Count: " + cowCount);
        System.out.println("Chicken Count: " + chickenCount);
        System.out.println("Rooster Count: " + roosterCount);
        System.out.println("Wolf Count: " + wolfCount);
        System.out.println("Lion Count: " + lionCount);
        System.out.println("Hunter Count: " + hunterCount);
    }
}

