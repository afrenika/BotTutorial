
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    boolean variantB1 = false;
    boolean variantB2 = false;
    int variant;


    @Override
    public String getBotUsername() {
        return "MathChatGABot";
    }

    @Override
    public String getBotToken() {
        return "5662189385:AAHY04K-tK7hQtzhvU0By-SstK_mebVQlb0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            var msg = update.getMessage();
            var user = msg.getFrom();
            var id= user.getId();
            if(msg.isCommand()){
                if(msg.getText().equals("/start")){
                    sendHello(id);}
                if(msg.getText().equals("/variant")){
                    sendVariantText(id);
                    variantB1 = true;}}
            else if(variantB1){
                if(!variantB2){
                    if((Arrays.asList("1", "2", "3", "4", "5", "6", "7" ).contains(msg.getText()))) {
                        variant = Integer.parseInt(msg.getText());
                        variantB2 = true;
                        sendText(id, "Вариант принят.\nВведите значения переменных:");
                        sendVariantDescription(id);}}
                else {
                    sendText(id, "Вариант: " + variant + "\nВведенные переменные: " + msg.getText() + "\nОтвет: " + MathFunc.func(variant, msg.getText().split(" ")));
                    variantB1 = false;
                    variantB2 = false;
                }}}
        else if (update.hasCallbackQuery()){
            var msg = update.getCallbackQuery().getMessage();
            var id= msg.getChatId();
            if(variantB1) {
                variant = Integer.parseInt(update.getCallbackQuery().getData());
                variantB2 = true;
                sendText(id, "Вариант принят.\nВведите значения переменных:");
                sendVariantDescription(id);}}}




    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();
        setButtons(sm);
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public void sendVariantText(Long who){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text("Введите номер варианта (целое число от 1 до 7) или просто нажмите на кнопку").build();
        sm.setReplyMarkup(setInline());//Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public void sendPhoto(Long who, File file){
        SendPhoto sendPhoto = SendPhoto.builder().chatId(who.toString()).photo(new InputFile(file)).build();
        try {
            execute(sendPhoto);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public void sendHello(Long who){
        String message = "Привет. Данный бот создан для расчета выражений по вариантам и введенным значениям переменным. Чтобы ввести номер варианта отправь команду /variant";
        sendText(who, message);
        sendPhoto(who, new File("src/main/resources/img.png"));
    }



    private InlineKeyboardMarkup setInline() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        for(int i = 1; i < 8; i++){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("V" + i);
            button.setCallbackData(String.valueOf(i));
            buttons1.add(button);
        }
        buttons.add(buttons1);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }

    public void sendVariantDescription(Long chatId){
        String[] vars = new String[] {"x, a, n, b, c", "x, a, y, e, w", "x, a0, a1, a2", "x, a", "a, b, c, d, e", "x, e", "x"};
        sendText(chatId, "Порядок ввода переменных через пробел: " + vars[variant - 1] );
        sendPhoto(chatId, new File("src/main/resources/v" + variant + ".png"));

    }

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/start"));
        keyboardFirstRow.add(new KeyboardButton("/variant"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}