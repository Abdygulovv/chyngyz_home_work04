import java.awt.*;
import java.util.Random;

public class Main {

        public static int bossHealth = 700;
        public static int bossDamage = 50;
        public static String bossDefence;
        public static int[] heroesHealth = {270, 260, 250, 320, 450, 280, 290, 240};
        public static int[] heroesDamage = {10, 15, 20, 0, 5, 18, 25, 22};
        public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky",
                "Berserk", "Thor"};
        public static int roundNumber = 0;
        public static String message = "";


        public static void main(String[] args) {
            printStatistics();
            while (!isGameFinished()) {
                playRound();
            }
        }
        public  static void Medic(){
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 3){
                    continue;
                }if (heroesHealth [i] > 0 && heroesHealth[i] < 100 && heroesHealth [3] > 0 ){
                    heroesHealth [i] += 30;
                    System.out.println("Doctor cured " + heroesAttackType [i]);
                    break;
                }

            }

        }
        public static void Golem(){
            Random random = new Random();
            boolean golem = random.nextBoolean();
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[4] > 0 && heroesHealth[i] > 0 && heroesHealth[4] != heroesHealth[i]){
                    heroesHealth[i] += bossDamage / 5;
                    heroesHealth[4] -= bossDamage / 5;
                }
                }

            }
            public static void Lucky (){
            Random random = new Random();
            boolean lucky = random.nextBoolean();
            if (heroesHealth [5] > 0 && lucky){
                heroesHealth[5] += bossDamage;
                System.out.println("Lucky Hit " + lucky);
            }

                }
                public static void Berserk(){
            Random random = new Random();
            int berserk1 = random.nextInt(10)+1;
            int berserk2 = random.nextInt(5)+1;
            if (heroesHealth [6] > 0 && bossHealth > 0){
                switch (berserk2){
                    case 1:
                        heroesDamage[6] = (heroesDamage[6] + bossDamage) - berserk1;
                        System.out.println("returns blow " + berserk2);
                        break;
                    case 2:
                        bossDamage = 50;
                        break;
                    case 3:
                        bossDamage = 50;
                        break;
                }
            }
                }
                public static void BerserkHit (){
            Random random = new Random();
            boolean berserkHit = random.nextBoolean();
            if (berserkHit){
                bossDamage = 0;
                System.out.println("Berserk stun ");
            }else {
                bossDamage = 50;
            }
                }
                public static void Thor (){
            Random random = new Random();
            boolean thor = random.nextBoolean();
                    for (int i = 0; i < heroesHealth.length; i++) {
                        if (heroesHealth[7] > 0){
                            if (thor){
                                bossDamage = 0;
                                System.out.println("Thor stunned " + thor);
                                break;
                            }
                        }else{
                            bossDamage = 50;
                            break;
                        }

                    }
                }



        public static void playRound() {
            roundNumber++;
            message = "";
            chooseBossDefence();
            bossHits();
            heroesHit();
            printStatistics();
            Medic();
            Golem();
            Lucky();
            Berserk();
            BerserkHit();
            Thor();
        }

        public static void chooseBossDefence() {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
            bossDefence = heroesAttackType[randomIndex];
        }

        public static void bossHits() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }

        public static void heroesHit() {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    int damage = heroesDamage[i];
                    if (heroesAttackType[i] == bossDefence) {
                        Random random = new Random();
                        int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10;
                        damage = damage * coefficient;
                        message = "Critical damage: " + damage;
                    }
                    if (bossHealth - damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - damage;
                    }
                }
            }
        }

        public static boolean isGameFinished() {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss won!!!");
            }
            return allHeroesDead;
        }

        public static void printStatistics() {
            System.out.println("ROUND " + roundNumber + " ----------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
            System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                    + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                        + " damage: " + heroesDamage[i]);
            }
            System.out.println(message);
        }
    }

