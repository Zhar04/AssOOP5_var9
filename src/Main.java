import java.util.ArrayList;
import java.util.List;

class Zoo {
    private String name;
    private List<Cell> cells;

    public Zoo(String name) {
        this.name = name;
        this.cells = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void removeCell(Cell cell) {
        cells.remove(cell);
    }

    public Animal searchAnimalByName(String name) {
        for (Cell cell : cells) {
            Animal foundAnimal = cell.searchAnimalByName(name);
            if (foundAnimal != null) {
                return foundAnimal;
            }
        }
        return null;
    }
}

class Cell {
    private int number;
    private int size;
    private int maxNumberOfAnimals;
    private List<Animal> animals;

    public Cell(int number, int size, int maxNumberOfAnimals) {
        this.number = number;
        this.size = size;
        this.maxNumberOfAnimals = maxNumberOfAnimals;
        this.animals = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }

    public void setMaxNumberOfAnimals(int maxNumberOfAnimals) {
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        if (animals.size() < maxNumberOfAnimals) {
            animals.add(animal);
            animal.setCell(this);
        } else {
            System.out.println("Cannot add more animals to this cell. Maximum capacity reached.");
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setCell(null);
    }

    public Animal searchAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }
}

class Animal {
    private String name;
    private boolean predator;
    private Cell cell;

    public Animal(String name, boolean predator) {
        this.name = name;
        this.predator = predator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPredator() {
        return predator;
    }

    public void setPredator(boolean predator) {
        this.predator = predator;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}

public class Main {
    public static void main(String[] args) {
        // A
        Zoo myZoo = new Zoo("My Zoo");
        Cell cell1 = new Cell(1, 50, 5);
        Cell cell2 = new Cell(2, 40, 3);
        myZoo.addCell(cell1);
        myZoo.addCell(cell2);

        Animal lion = new Animal("Lion", true);
        Animal tiger = new Animal("Tiger", true);
        Animal elephant = new Animal("Elephant", false);

        cell1.addAnimal(lion);
        cell1.addAnimal(tiger);
        cell2.addAnimal(elephant);

        System.out.println("Zoo Name: " + myZoo.getName());
        for (Cell cell : myZoo.getCells()) {
            System.out.println("Cell Number: " + cell.getNumber());
            for (Animal animal : cell.getAnimals()) {
                System.out.println("Animal: " + animal.getName() + " (Predator: " + animal.isPredator() + ")");
            }
            System.out.println();
        }

        // B
        Animal giraffe = new Animal("Giraffe", false);
        cell2.addAnimal(giraffe);

        System.out.println("Animals in Cell 2:");
        for (Animal animal : cell2.getAnimals()) {
            System.out.println("Animal: " + animal.getName() + " (Predator: " + animal.isPredator() + ")");
        }

        // C
        Animal foundAnimal = myZoo.searchAnimalByName("Tiger");
        if (foundAnimal != null) {
            System.out.println("Found Animal: " + foundAnimal.getName() +
                    " (Predator: " + foundAnimal.isPredator() +
                    ", Cell: " + foundAnimal.getCell().getNumber() + ")");
        } else {
            System.out.println("Animal not found.");
        }
    }
}
