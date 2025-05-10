package model;

/**
 * A DifficultyLevel enum a játék nehézségi szintjeit reprezentálja.
 * Minden szinthez tartoznak elvárt értékek: növényevők, ragadozók, pénz, látogatók és dzsipek száma.
 * <p>
 * Az enum elemei:
 * <ul>
 *   <li>{@link #EASY} - Könnyű szint, kevesebb állat, kevesebb pénz, kevesebb látogató és dzsip.</li>
 *   <li>{@link #MEDIUM} - Közepes szint, közepes mennyiségű állat, pénz, látogató és dzsip.</li>
 *   <li>{@link #HARD} - Nehéz szint, sok állat, sok pénz, sok látogató és dzsip.</li>
 * </ul>
 * <p>
 * Minden enum érték public, így az osztályon kívülről is elérhető, például: <code>DifficultyLevel.EASY</code>
 */
public enum DifficultyLevel {
    /**
     * Könnyű nehézségi szint.
     * public enum érték, így bárhonnan elérhető.
     */
    EASY(5,5,50000,25,1),
    /**
     * Közepes nehézségi szint.
     * public enum érték, így bárhonnan elérhető.
     */
    MEDIUM(10,10,150000,100,2),
    /**
     * Nehéz nehézségi szint.
     * public enum érték, így bárhonnan elérhető.
     */
    HARD(25,25,300000,500,3);

    /** Az elvárt növényevők száma. */
    private final int reqHerb;
    /** Az elvárt ragadozók száma. */
    private final int reqCarn;
    /** Az elvárt pénzmennyiség. */
    private final int reqMoney;
    /** Az elvárt látogatók száma. */
    private final int reqVisitor;
    /** Az elvárt dzsipek száma. */
    private final int reqJeep;

    /**
     * Létrehoz egy DifficultyLevel példányt a megadott paraméterekkel.
     * private, mert csak az enum elemei példányosíthatják.
     * @param reqHerb elvárt növényevők száma
     * @param reqCarn elvárt ragadozók száma
     * @param reqMoney elvárt pénzmennyiség
     * @param reqVisitor elvárt látogatók száma
     * @param reqJeep elvárt dzsipek száma
     */
    private DifficultyLevel(int reqHerb, int reqCarn, int reqMoney, int reqVisitor, int reqJeep){
        this.reqHerb = reqHerb;
        this.reqCarn = reqCarn;
        this.reqMoney = reqMoney;
        this.reqVisitor = reqVisitor;
        this.reqJeep = reqJeep;
    }

    /**
     * Visszaadja az elvárt növényevők számát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az értéket.
     * @return növényevők száma
     */
    public int getReqHerb() {
        return reqHerb;
    }

    /**
     * Visszaadja az elvárt ragadozók számát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az értéket.
     * @return ragadozók száma
     */
    public int getReqCarn() {
        return reqCarn;
    }

    /**
     * Visszaadja az elvárt dzsipek számát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az értéket.
     * @return dzsipek száma
     */
    public int getReqJeep() {
        return reqJeep;
    }

    /**
     * Visszaadja az elvárt látogatók számát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az értéket.
     * @return látogatók száma
     */
    public int getReqVisitor() {
        return reqVisitor;
    }

    /**
     * Visszaadja az elvárt pénzmennyiséget.
     * Ez a metódus public, hogy más osztályok is lekérhessék az értéket.
     * @return pénzmennyiség
     */
    public int getReqMoney() {
        return reqMoney;
    }
}
