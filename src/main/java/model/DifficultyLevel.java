package model;

public enum DifficultyLevel {
    EASY(5,5,50000,25,1),MEDIUM(10,10,150000,100,2),HARD(25,25,300000,500,3);

    private final int reqHerb;
    private final int reqCarn;
    private final int reqMoney;
    private final int reqVisitor;
    private final int reqJeep;

    private DifficultyLevel(int reqHerb, int reqCarn, int reqMoney, int reqVisitor, int reqJeep){
        this.reqHerb = reqHerb;
        this.reqCarn = reqCarn;
        this.reqMoney = reqMoney;
        this.reqVisitor = reqVisitor;
        this.reqJeep = reqJeep;

    }

    public int getReqHerb() {
        return reqHerb;
    }
    public int getReqCarn() {
        return reqCarn;
    }
    public int getReqJeep() {
        return reqJeep;
    }
    public int getReqVisitor() {
        return reqVisitor;
    }
    public int getReqMoney() {
        return reqMoney;
    }
}
