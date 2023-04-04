import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();                  //We moved this line out of the register method, to access it later
        bot.setMap(addMap());
        botsApi.registerBot(bot);
    }

    public static HashMap<Long, User_b> addMap(){
        File fileIDName = new File("fileIDName");
        HashMap<Long, User_b> idFileName = new HashMap<>();
        if (fileIDName.exists()) {
            try (FileInputStream f = new FileInputStream(fileIDName);
                 ObjectInputStream s = new ObjectInputStream(f)) {
                idFileName = (HashMap<Long, User_b>) s.readObject();}
            catch (IOException | ClassNotFoundException e) {
                return new HashMap<>();}}
        return idFileName;}
}
