class DefaultUsers {

    void createUsers() {
        GsonCommand gsonCommand =  new GsonCommand();
        //Users admin = new Users("admin", "12345");
        Users yabadabadoo =  new Users("yabadaba", "11111");

        //gsonCommand.saveToFile(admin);
        gsonCommand.saveToFile(yabadabadoo);
    }
}
