package _12_concurrency._15_live_locks;

public class PoliteSpouse {
    public static void main(String[] args) {

        // A husband and wife are trying to eat soup, but only have one spoon between them.
        // Each spouse is too polite, and will pass the spoon if the other has not yet eaten.

        final Diner husband = new Diner("Bob");
        final Diner wife = new Diner("Alice");

        final Spoon spoon = new Spoon(husband);

        new Thread(new Runnable() {
            public void run() {
                husband.eatWith(spoon, wife);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                wife.eatWith(spoon, husband);
            }
        }).start();
    }

    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            owner = d;
        }

        public Diner getOwner() {
            return owner;
        }

        public synchronized void setOwner(Diner d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten!", owner.name);
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            this.isHungry = true;
        }

        public String getName() {
            return name;
        }

        public boolean isHungry() {
            return isHungry;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Don't have the spoon, so wait patiently for spouse.
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue;
                }

                // If spouse is hungry, insist upon passing the spoon.
                if (spouse.isHungry()) {
                    System.out.printf("%s: You eat first my darling %s!%n", name, spouse.getName());
                    spoon.setOwner(spouse);
                    continue;
                }

                // Spouse wasn't hungry, so finally eat
                spoon.use();
                isHungry = false;
                System.out.printf("%s: I am stuffed, my darling %s!%n", name, spouse.getName());
                spoon.setOwner(spouse);
            }
        }
    }
}
