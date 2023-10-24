import java.util.Comparator;
public class NumGameWin implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user2.getNumGameWin() - user1.getNumGameWin();
    }
}
