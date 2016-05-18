package sagar.memories;

import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 13-05-2016.
 */
public class FfMessages {
    public static String getMessage(String personName){
        switch (personName){
            case Globals.KEY_ADI: return getAboutAdi();
            case Globals.KEY_DIPAN: return getAboutDipan();
            case Globals.KEY_KRISHNA: return getAboutKrishna();
            case Globals.KEY_SUVI: return getAboutSuvi();
        }
        return null;
    }

    public static String getAboutAdi(){
        String msg = "<b>Nickname:</b> Adi, Golu<br><br>"+
        "<b>From:</b> <br>\t\"Jagtat Germani Bhartat Parbhani\"<br><br>"+
        "<b>Favourite Status:</b> <br>\t\"Work Until Your Idols Become Your Rivals\"<br><br>"+
        "<b>Favourtie Color:</b> Red<br><br>"+
        "<b>BFF:</b> Ashumalini Mishra <br>(As per everyone, Except Adi)<br><br>"+

        "Aditya Dahale is a very caring and trustworthy person I ever met.<br><br>"+
        "He is a great chess player almost won every game from each of us, except me, because I never played with him.<br><br>"+
        "Aditya is my newspaper who knows almost every news around the world. He like technical section most and a great fan of Tesla Motors.<br><br>"+
        "Keeping a secret from Aditya is a very difficult task. He got something that I am never able to keep secret in my tummy.<br>";
        return msg;
    }

    public static String getAboutDipan(){
        String msg = "<b>Nickname:</b> Dipan<br>"+
        "<b>From:</b> Ballarshah, MH<br>"+
        "<b>Favourite Song:</b> Pal Pal(Munna Bhai)<br>"+
        "<b>Favourite Dish:</b> Berkut<br>"+
        "<b>Hobby:</b> Watching TV Series<br>"+
        "<b>Favourite Time Pass:</b> L3<br><br>"+

        "When you hear Dipan's name first time, you need at least 4-7 tries to pronounce his name properly.<br>"+
        "He is also known sometimes as Dipak, Dipaan, Dipen.<br><br>"+

        "Dipan Mirgani likes to visit various places. He is our group photographer and captures every moment of our trip, "+
        "but never comes in any picture.<br><br>"+

        "Dipan is very loyal to his responsibilities.<br><br>"+

        "Dipan cares for everyone and never try to hurt anyone \"intentionally\".<br><br>"+

        "He is always ready to help others and have a kind heart.<br>"+
        "He never let you down during hard times.<br><br> He is the interesting, weird but best person I ever met.";

        return msg;
    }

    public static String getAboutKrishna(){
        String msg = "<b>Nickname:</b> Krish, Miss Literary<br>"+
        "<b>From:</b> Nanded, Maharashtra<br>"+
        "<b>Synonym:</b> Jugaadu<br>"+
        "<b>Hobby:</b> Reading Novels, Writing, Making Friends<br><br>"+

        "Krisha is a very colorful person who wants to try every taste of life.<br><br>"+

        "She is a very social person and I guess, she has highest friend's count in college.<br><br>"+

        "Krishna is also very ambitious and want lot of things in life. She is very self confident to achieve her dreams.<br><br>"+

        "Krishna may seem hard sometimes from outside but actually she has a very soft and pure heart.<br><br>"+

        "Gaining trust of Krishna is very easy but facing her anger is most horrible thing.<br><br>"+

        "Krishna is very caring and supportive person.<br><br>"+

        "She enjoys every moment of life, good as well as bad, but stays logical in every condition";
        return msg;
    }
    public static String getAboutSuvi(){
        String msg = "<b>Nickname:</b> Suvi, Sui<br>"+
        "<b>From:</b> Pipariya, MP<br>"+
        "<b>Hobby:</b> Making friends, Travel, TT<br>"+
        "<b>Synonym:</b> Lady Bheem<br><br>"+

        "Suvidha is an adventures person who loves to try new things. She recently made a 83 metres Bungee jump from Rushikesh.<br><br>"+

        "She is a very good listener, Open-Minded and very caring."+
        "Having her around is a great fun.<br><br>"+

        "When it comes to Suvidha only one things come in mind:-<br>"+
        "\"Life isn't about finding yourself. Life is about creating yourself\"<br><br>"+

        "Suvidha is very well organized and places a great example of making balance between life and work.<br><br>"+

        "You can actually learn loads of things from her.";
        return msg;
    }
}
