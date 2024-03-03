import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlagleModel {

    private final List<Observer<FlagleModel, String>> observerList = new LinkedList<>();

    public static String MAIN_MENU = "Main Menu";
    public static String GENDER = "Gender identity";

    public static String SEXUALITY = "Sexuality";

    public static String ALL = "Gender identity/Sexuality";

    public static String ATTEMPT1 = "Attempt 1/4";
    public static String ATTEMPT2 = "Attempt 2/4";
    public static String ATTEMPT3 = "Attempt 3/4";
    public static String ATTEMPT4 = "Attempt 4/4";

    public static String FAILURE = "You fail";
    public static String OVER = "Thank you for playing";
    public static String END = "end";
    public static String CONT = "cont";

    /**
     * Number of attempt
     */
    public static int attemptNum = 0;

    /**
     * If show hint or not
     */
    public boolean hint;

    public static String FLAG_LOADED = "Flag Loaded";
    /**
     * ArrayList for images of gender flags
     */
    private static ArrayList<ArrayList> flagsGender;

    /**
     * ArrayList for images of sexuality flags
     */
    private static ArrayList<ArrayList> flagsSexuality;

    /**
     * ArrayList of images for all flags
     */
    private static ArrayList<ArrayList> flagsAll;

    /**
     * User score
     */
    private int score = 000;

    private boolean nextFlag;
    /**
     * Resource directory for flag images
     */
    private final static String RESOURCES_DIR = "Flags/";


    public void addObserver(Observer<FlagleModel, String> observer){
        this.observerList.add(observer);
    }

    public void updateScore(int np){
        this.score += np;
    }

    public String getScore(){
        return String.valueOf(this.score);
    }


    public void setGenderFlags() throws FileNotFoundException{
        flagsGender = new ArrayList<>();
        //demigender
        ArrayList demigender = new ArrayList();
        demigender.add("Demigender");
        demigender.add("A person who has a partial, but not full connection to a specific gender or the idea of gender. ");
        demigender.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Demigender.png")));
        demigender.add("Sand Chang");
        demigender.add("A clinical psychologist and educator. Uses a number of terms for themself including demiboy. They are also an " +
                "esquity consultant with a passion for justice, education, and healing-centered engagement. They pride themselves" +
                " on bringing an inclusive and psychologically safer environments. ");
        demigender.add(new Image(getClass().getResourceAsStream("Women/Sand.png")));
        flagsGender.add(demigender);

        //agender
        ArrayList agender = new ArrayList();
        agender.add("Agender");
        agender.add("A person who doesn't identify as any gender, sometimes defined as having a “lack of gender”.");
        agender.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Agender.png")));
        agender.add("Jazmin Bean");
        agender.add("English singer songwriter, Jazmin Bean breaks the molds with their surreal and bold makeup," +
                "fashion and sound. Bean's music has been described as pop metal, hyperpop, alt-pop, grunge and genre-bending" +
                "Bean also run 'Cult Candy Cosmetics', a cruelty, vegan makeup brand.");
        agender.add(new Image(getClass().getResourceAsStream("Women/Jazmin.jpg")));
        flagsGender.add(agender);


        //transgender
        ArrayList transgender = new ArrayList();
        transgender.add("Transgender");
        transgender.add("A person whose gender identity doesn’t correspond with the sex registered to them at birth.");
        transgender.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Transgender.png")));
        transgender.add("Marsha P.Johnson");
        transgender.add("One of the most prominent figures of the gay rights movement in the 1960s and 70s. Her activism" +
                " began at the frontlines of the Stonewall riots, a response to a violent police raid at the Stonewall " +
                " Inn in New York City.");
        transgender.add(new Image(getClass().getResourceAsStream("Women/Marsha.png")));
        flagsGender.add(transgender);

        //nonbinary
        ArrayList nonbinary = new ArrayList();
        nonbinary.add("Nonbinary");
        nonbinary.add("A gender identity that does not fall into the binary categories of male or female.");
        nonbinary.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Nonbinary.png")));
        nonbinary.add("Caldwell Tidicue, 'Bob the Drag Queen'");
        nonbinary.add("Non-binary comic and actor best known for his alter ego. 'Bob the Drag Queen.' He went on to win " +
                " the 8th season of RuPaul's Drag Race and founded the <i> Black Queer Town Hall</i> with fellow drag queen" +
                "  Peppermint. The non-profit celebrates Black queer exellence through fundraising, panels, and performances.");
        nonbinary.add(new Image(getClass().getResourceAsStream("Women/Bob.png")));
        flagsGender.add(nonbinary);

        //intersex
        ArrayList intersex = new ArrayList();
        intersex.add("Intersex");
        intersex.add("A person who is born with physical, hormonal, and/or genetic features that are neither wholly female nor wholly male or are a combination of female and male.");
        intersex.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Intersex.png")));
        intersex.add("Lili Elbe");
        intersex.add("Born in Denmark in 1882, Lili became one of the first known recipients of sex reassignment surgery" +
                " in 1930. A former landscape painter, Elbe was the winner of the prestigious Neuhausens prize in 1907.");
        intersex.add(new Image(getClass().getResourceAsStream("Women/Elbe.png")));
        flagsGender.add(intersex);

        //genderfluid
        ArrayList genderfluid = new ArrayList();
        genderfluid.add("Genderfluid");
        genderfluid.add("A nonbinary gender identity or expression that is not fixed and is capable of changing over time, they don’t follow the rules of gender. ");
        genderfluid.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Genderfluid.png")));
        genderfluid.add("Miley Cyrus");
        genderfluid.add("American singer, songwriter, and actress, famously known for her evolving artistry from a child start to grammy-winner for her " +
                " certified double-platinum single 'Flowers.'");
        genderfluid.add(new Image(getClass().getResourceAsStream("Women/Miley.jpg")));
        flagsGender.add(genderfluid);

        //genderqueer
        ArrayList genderqueer = new ArrayList();
        genderqueer.add("Genderqueer");
        genderqueer.add("A person whose gender identity does not correspond to exclusively male or female and is outside the norms of hetero-, or homo-sexuality.");
        genderqueer.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Genderqueer.png")));
        genderqueer.add("Austin Cocktails");
        genderqueer.add("(Dipicted Left -> Right, Kelly Gasink, Jill Burns) While not members of the genderqueer community we would like to bring" +
                " attention to women owned companies like 'Austin Cocktails'. This company was inspired by thier grandfather's tradition" +
                ", the two fostered a brand revitalizing the connections made during 'cocktail hour'.");
        genderqueer.add(new Image(getClass().getResourceAsStream("Women/KellyJill.png")));
        flagsGender.add(genderqueer);

        //bigender
        ArrayList bigender = new ArrayList();
        bigender.add("Bigender");
        bigender.add("A person whose gender identity encompasses two genders. ");
        bigender.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Gender - Bigender.png")));
        bigender.add("Archer Rose");
        bigender.add(" (Dipicted is Marian Leitner-Waldman) While not a member of the bigender community 'Archer Rose' support all forms of identity, and alongside" +
                "her husband, the two founded Archer Rose to create a more approachable format to luxury wine. Partnering with" +
                " small winemakers, Archer Rose prides themselves in their connections with suppliers and consumers.");
        bigender.add(new Image(getClass().getResourceAsStream("Women/Marian.png")));
        flagsGender.add(bigender);

    }

    public void setSexualityFlags() throws FileNotFoundException{
        flagsSexuality = new ArrayList<>();
        //transgay
        ArrayList transgay = new ArrayList();
        transgay.add("Gay");
        transgay.add("Men assigned at birth or trans-men who are sexually or romantically attracted exclusively to other men.\n");
        transgay.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Trans_gay.png")));
        flagsSexuality.add(transgay);

        //lesbian
        ArrayList lesbian = new ArrayList();
        lesbian.add("Lesbian");
        lesbian.add("Women who are sexually or romantically attracted exclusively to other women.");
        lesbian.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Lesbian.png")));
        flagsSexuality.add(lesbian);

        //bisexual
        ArrayList bisexual = new ArrayList();
        bisexual.add("Bisexual");
        bisexual.add("Someone who is sexually, emotionally,  or romantically attracted to both men or women or more than one sex or gender.");
        bisexual.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Bisexual.png")));
        flagsSexuality.add(bisexual);

        //pansexual
        ArrayList pansexual = new ArrayList();
        pansexual.add("Pansexual");
        pansexual.add("Attraction to people  sexually, romantically, or emotionally of any gender identity, gender, or biological sex.");
        pansexual.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Pansexual.png")));
        flagsSexuality.add(pansexual);

        //polyamory
        ArrayList polyamory = new ArrayList();
        polyamory.add("Polyamory");
        polyamory.add("The practice of having multiple intimate relationships whether sexual or just romantic, with the full knowledge and consent of all parties involved.\n");
        polyamory.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Polyamory.png")));
        flagsSexuality.add(polyamory);

        //asexual
        ArrayList asexual = new ArrayList();
        asexual.add("Asexual");
        asexual.add("A person who experiences little to no sexual attraction to others, or a low interest in sexual activity (Not the same as celibacy or abstinence, because they are choices).");
        asexual.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Asexual.png")));
        flagsSexuality.add(asexual);

        //demisexual
        ArrayList demisexual = new ArrayList();
        demisexual.add("Demisexual");
        demisexual.add("A person who only experiences sexual attraction after making a strong emotional connection with a specific person.\n");
        demisexual.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Demisexual.png")));
        flagsSexuality.add(demisexual);

        //polysexual
        ArrayList polysexual = new ArrayList();
        polysexual.add("Polysexual");
        polysexual.add("Attraction to any person of certain genders preferred by the specific person, doesn’t have a concrete definition.\n");
        polysexual.add(new Image(getClass().getResourceAsStream(RESOURCES_DIR + "Sexuality - Polysexual.png")));
        flagsSexuality.add(polysexual);
    }

    public void setAllFlags() throws FileNotFoundException{
        flagsAll = new ArrayList<>();
        flagsAll.addAll(flagsSexuality);
        flagsAll.addAll(flagsGender);
    }

    public void setup() throws FileNotFoundException{
        flagsSexuality = null;
        flagsGender = null;
        flagsAll = null;
        setGenderFlags();
        setSexualityFlags();
        setAllFlags();
        score = 0;

        alertObservers(MAIN_MENU);
    }

    public ArrayList getIdentity(String cat) throws FileNotFoundException {
        ArrayList identity = null;
        int rand;
        if(!flagsGender.isEmpty() && cat.equals(GENDER)) {
            rand = (int)((Math.random() * (flagsGender.size()-1)));
            identity = flagsGender.get(rand);
            flagsGender.remove(flagsGender.get(rand));
            flagsGender.trimToSize();
        } else if(!flagsSexuality.isEmpty() && cat.equals(SEXUALITY)){
            rand = (int)((Math.random() * (flagsSexuality.size()-1)));
            identity = flagsSexuality.get(rand);
            flagsSexuality.remove(flagsSexuality.get(rand));
            flagsSexuality.trimToSize();
        } else if(!flagsAll.isEmpty() && cat.equals(ALL)){
            rand = (int)((Math.random() * (flagsAll.size()-1)));
            identity = flagsAll.get(rand);
            flagsAll.remove(flagsAll.get(rand));
            flagsAll.trimToSize();
        }
        alertObservers(FLAG_LOADED);
        return identity;
    }

    public void gameStart(){
        attemptNum = 1;
        alertObservers(ATTEMPT1);
    }

    public int getAttemptNum(){
        return attemptNum;
    }

    public void setAttemptNum(int num){
        attemptNum = num;
        switch(attemptNum){
            case 1: alertObservers(ATTEMPT1);
                break;
            case 2: alertObservers(ATTEMPT2);
                break;
            case 3: alertObservers(ATTEMPT3);
                break;
            case 4: alertObservers(ATTEMPT4);
                break;
            case 5: alertObservers(FAILURE);
                break;
        }
    }

    public void alertObservers(String data){
        for(var observer : observerList){
            observer.update(this, data);
        }
    }

    public boolean isNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(boolean nextFlag) {
        this.nextFlag = nextFlag;
        if(nextFlag) alertObservers(END);
        else alertObservers(CONT);
    }
}