package Logic;

public enum Trophy {
    Platino(5, "Platino"), Oro(3, "Oro"), Plata(2, "Plata"), Bronce(1, "Bronce");
    
    public final int Points;
    public final String Type;
    
    Trophy(int Points, String Type){
        this.Points = Points;
        this.Type = Type;
    }
    
    @Override
    public String toString(){
        return "Trofeo de " + Type + "\t Pts. " + Points;
    }
    
}
