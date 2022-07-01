import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private char colorSymbol;
    private String color;
    private String userName;
    private boolean winStatus;

    public Player(char colorSymbol, String color, String userName) {
        this.colorSymbol = colorSymbol;
        this.color = color;
        this.userName = userName;
        this.winStatus = false;
    }

}
