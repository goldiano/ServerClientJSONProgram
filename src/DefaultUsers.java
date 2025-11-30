import java.util.ArrayList;
import java.util.List;

class DefaultUsers {

    private List<Users> usersChat = new ArrayList<>();

    DefaultUsers() {
        GsonCommand gsonCommand =  new GsonCommand();

        Users admin = new Users("admin", "12345",true);
        Users yabadabadoo =  new Users("yabadaba", "11111",false);

        usersChat.add(admin);
        usersChat.add(yabadabadoo);

        gsonCommand.saveToFile(admin);
        gsonCommand.saveToFile(yabadabadoo);
    }

    boolean findByName(String name, String password) {
        for(Users i : usersChat) {
            if(i.getName().equals(name) && i.getPass().equals(password)) return true;
        }
        return false;
    }
}
