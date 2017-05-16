package nl.saxion.ap.drink.model;

import java.util.ArrayList;

import nl.saxion.ap.drink.R;

/**
 * Created by Roy on 13-3-2017.
 */

public class DataModel {


    private static DataModel dataModel = null;


    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> namesDone = new ArrayList<>();

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<UsedCard> cardsDone = new ArrayList<>();

    private Card currentCard;
    private Card previousCard;
    private Card defaultCard;
    private int rightStreak;
    private String currentName;
    private String previousName;
    private int currentNameIndex;
    private int streakEnd;

    private DataModel() {
        initDrawables();
        defaultCard = new Card("Achterkant", 0, 0, R.drawable.back_card);
        currentCard = defaultCard;
        rightStreak = 0;
        currentNameIndex = 0;
        streakEnd = 8;

//        names.add("Roy");
//        names.add("Dennis");
//        names.add("Dave");
    }

    public int getStreakEnd() {
        return streakEnd;
    }

    public void setStreakEnd(int streakEnd) {
        this.streakEnd = streakEnd;
    }

    public static DataModel getInstance() {
        if (dataModel == null) {
            dataModel = new DataModel();
        }
        return dataModel;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public String getPreviousName() {
        return previousName;
    }

    public void addName(String name) {
        names.add(name);
    }

    public void addName(int index, String name) {
        names.add(index, name);
    }

    public void removeName(int index) {
        names.remove(index);
    }

    public String getCurrentName() {
        return currentName;
    }

    public String getName(int index) {
        return names.get(index);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Card getDefaultCard() {
        return defaultCard;
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public int getCardsLeft() {
        return cards.size();
    }

    public ArrayList<UsedCard> getUsedCards() {
        return cardsDone;
    }

    public void firstCard() {
        previousCard = currentCard;
        int index = (int) (Math.random() * cards.size());
        currentCard = cards.get(index);
        cards.remove(index);
        nextName();
    }

    public void nextCard() {
        previousCard = currentCard;
        if (cards.size() != 0) {
            int index = (int) (Math.random() * cards.size());
            currentCard = cards.get(index);
            cards.remove(index);
            nextName();
        } else {
            currentCard = defaultCard;
            initDrawables();
        }
    }

    public void nextName() {
        previousName = currentName;
        currentName = names.get(currentNameIndex);
        if (currentNameIndex == names.size() - 1) {
            currentNameIndex = 0;
        } else {
            currentNameIndex++;
        }
    }

    public void saveTurn(int state, boolean userInput) {
        boolean right = false;
        if (state == 1 && userInput) {
            right = true;
            rightStreak++;
        } else if (state == -1 && !userInput) {
            right = true;
            rightStreak++;
        }
        cardsDone.add(new UsedCard(currentCard, previousCard, state, right, currentName));
    }

    public void checkNameOrder() {
        if(currentNameIndex==0){
            currentName = names.get(names.size()-1);
        }else {
            currentName = names.get(currentNameIndex - 1);
        }
    }

    public void initDrawables() {
        cardsDone.clear();

        cards.add(new Card("Harten 2", 2, 0, R.drawable.harten_2));
        cards.add(new Card("Harten 3", 3, 0, R.drawable.harten_3));
        cards.add(new Card("Harten 4", 4, 0, R.drawable.harten_4));
        cards.add(new Card("Harten 5", 5, 0, R.drawable.harten_5));
        cards.add(new Card("Harten 6", 6, 0, R.drawable.harten_6));
        cards.add(new Card("Harten 7", 7, 0, R.drawable.harten_7));
        cards.add(new Card("Harten 8", 8, 0, R.drawable.harten_8));
        cards.add(new Card("Harten 9", 9, 0, R.drawable.harten_9));
        cards.add(new Card("Harten 10", 10, 0, R.drawable.harten_10));
        cards.add(new Card("Harten Boer", 11, 0, R.drawable.harten_boer));
        cards.add(new Card("Harten Koningin", 12, 0, R.drawable.harten_koningin));
        cards.add(new Card("Harten Koning", 13, 0, R.drawable.harten_koning));
        cards.add(new Card("Harten Aas", 14, 0, R.drawable.harten_aas));

        cards.add(new Card("Klaver 2",2,1,R.drawable.klaver_2));
        cards.add(new Card("Klaver 3",3,1,R.drawable.klaver_3));
        cards.add(new Card("Klaver 4",4,1,R.drawable.klaver_4));
        cards.add(new Card("Klaver 5",5,1,R.drawable.klaver_5));
        cards.add(new Card("Klaver 6",6,1,R.drawable.klaver_6));
        cards.add(new Card("Klaver 7",7,1,R.drawable.klaver_7));
        cards.add(new Card("Klaver 8",8,1,R.drawable.klaver_8));
        cards.add(new Card("Klaver 9",9,1,R.drawable.klaver_9));
        cards.add(new Card("Klaver 10",10,1,R.drawable.klaver_10));
        cards.add(new Card("Klaver Boer",11,1,R.drawable.klaver_boer));
        cards.add(new Card("Klaver Koning",13,1,R.drawable.klaver_koning));
        cards.add(new Card("Klaver Koningin",12,1,R.drawable.klaver_koningin));
        cards.add(new Card("Klaver Aas", 14, 0, R.drawable.klaver_aas));

        cards.add(new Card("Ruiten 2",2,2,R.drawable.ruiten_2));
        cards.add(new Card("Ruiten 3",3,2,R.drawable.ruiten_3));
        cards.add(new Card("Ruiten 4",4,2,R.drawable.ruiten_4));
        cards.add(new Card("Ruiten 5",5,2,R.drawable.ruiten_5));
        cards.add(new Card("Ruiten 6",6,2,R.drawable.ruiten_6));
        cards.add(new Card("Ruiten 7",7,2,R.drawable.ruiten_7));
        cards.add(new Card("Ruiten 8",8,2,R.drawable.ruiten_8));
        cards.add(new Card("Ruiten 9",9,2,R.drawable.ruiten_9));
        cards.add(new Card("Ruiten 10",10,2,R.drawable.ruiten_10));
        cards.add(new Card("Ruiten Boer",11,2,R.drawable.ruiten_boer));
        cards.add(new Card("Ruiten Koning",13,2,R.drawable.ruiten_koning));
        cards.add(new Card("Ruiten Koningin",12,2,R.drawable.ruiten_koningin));
        cards.add(new Card("Ruiten Aas", 14, 2, R.drawable.ruiten_aas));

        cards.add(new Card("Schoppen 2",2,3,R.drawable.schoppen_2));
        cards.add(new Card("Schoppen 3",3,3,R.drawable.schoppen_3));
        cards.add(new Card("Schoppen 4",4,3,R.drawable.schoppen_4));
        cards.add(new Card("Schoppen 5",5,3,R.drawable.schoppen_5));
        cards.add(new Card("Schoppen 6",6,3,R.drawable.schoppen_6));
        cards.add(new Card("Schoppen 7",7,3,R.drawable.schoppen_7));
        cards.add(new Card("Schoppen 8",8,3,R.drawable.schoppen_8));
        cards.add(new Card("Schoppen 9",9,3,R.drawable.schoppen_9));
        cards.add(new Card("Schoppen 10",10,3,R.drawable.schoppen_10));
        cards.add(new Card("Schoppen Boer",11,3,R.drawable.schoppen_boer));
        cards.add(new Card("Schoppen Koning",13,3,R.drawable.schoppen_koning));
        cards.add(new Card("Schoppen Koningin",12,3,R.drawable.schoppen_koningin));
        cards.add(new Card("Schoppen Aas", 14, 3, R.drawable.schoppen_aas));
    }

    public int getRightStreak() {
        if (rightStreak == 0) {
            return 1;
        }
        return rightStreak;
    }

    public void setRightStreak(int rightStreak) {
        this.rightStreak = rightStreak;
    }

    public void resetGame(){
        cards.clear();
        cardsDone.clear();

        initDrawables();
        defaultCard = new Card("Achterkant", 0, 0, R.drawable.back_card);
        currentCard = defaultCard;
        rightStreak = 0;
        currentNameIndex = 0;
        streakEnd = 8;
        previousCard=null;
        currentName = null;
        previousName=null;
    }
}
