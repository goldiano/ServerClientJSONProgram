class ServerResponse {

    //String status;
    private String message;

    ServerResponse(String message) {
        //this.status = status;
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
