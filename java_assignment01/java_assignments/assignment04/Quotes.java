package java_assignments.assignment04;

import java.util.ArrayList;

public class Quotes
{
    public ArrayList<String> quotes = new ArrayList<>();
    public ArrayList<Integer> day = new ArrayList<>();
    public ArrayList<Integer> month = new ArrayList<>();
    public ArrayList<String> specialQuote = new ArrayList<>();

    Quotes()
    {
        day.add(1);
        month.add(1);
        specialQuote.add("Happy New Year");

        day.add(23);
        month.add(1);
        specialQuote.add("Happy Parakaram Diwas");

        day.add(26);
        month.add(1);
        specialQuote.add("Happy Republic Day");

        day.add(14);
        month.add(2);
        specialQuote.add("Happy Valentines Day");

        day.add(15);
        month.add(7);
        specialQuote.add("Happy Independence Day");

        day.add(1);
        month.add(5);
        specialQuote.add("Happy Labour Rights Day");

        day.add(2);
        month.add(10);
        specialQuote.add("Happy Mahatma Jayanti");

        day.add(25);
        month.add(11);
        specialQuote.add("Happy Thanksgiving");

        day.add(25);
        month.add(12);
        specialQuote.add("Merry Christmas");
        //C:\Program Files (x86)\Common Files\Oracle\Java\javapath

        quotes.add("Love For All, Hatred For None => Khalifatul Masih III");
        quotes.add("Change the world by being yourself => Amy Poehler");
        quotes.add("Every moment is a fresh beginning => T.S Eliot");
        quotes.add("Never regret anything that made you smile => Mark Twain");
        quotes.add("Die with memories, not dreams => Unknown");
        quotes.add("Aspire to inspire before we expire => Satabda Mandal");
        quotes.add("Everything you can imagine is real => Pablo Picasso");
        quotes.add("Simplicity is the ultimate sophistication => Leonardo da Vinci");
        quotes.add("Whatever you do, do it well => Walt Disney");
        quotes.add("What we think, we become => Buddha");
        quotes.add("All limitations are self-imposed => Oliver Wendell Holmes");
        quotes.add("Tough times never last but tough people do => Robert H. Schiuller");
        quotes.add("Problems are not stop signs, they are guidelines => Robert H. Schiuller");
        quotes.add("Problems are not stop signs, they are guidelines => Robert H. Schiuller");
        quotes.add("One day the people that don_t even believe in you will tell everyone how they met you => Johnny Depp");
        quotes.add("If I_m gonna tell a real story, I_m gonna start with my name => Kendrick Lamar");
        quotes.add("If you tell the truth you don_t have to remember anything => Mark Twain");
        quotes.add("Have enough courage to start and enough heart to finish => Jessica N. S. Yourko");
        quotes.add("Hate comes from intimidation, love comes from appreciation => Tyga");
        quotes.add("I could agree with you but then we_d both be wrong => Harvey Specter");
        quotes.add("Oh, the things you can find, if you don_t stay behind => Dr. Seuss");
        quotes.add("Determine your priorities and focus on them => Eileen McDargh");
        quotes.add("Be so good they can_t ignore you => Steve Martin");
        quotes.add("Dream as if you_ll live forever, live as if you_ll die today => James Dean");
        quotes.add("Yesterday you said tomorrow. Just do it => Nike");
        quotes.add("I don_t need it to be easy, I need it to be worth it => Lil Wayne\n");
        quotes.add("Never let your emotions overpower your intelligence => Drake");
        quotes.add("Nothing lasts forever but at least we got these memories => J. Cole");
        quotes.add("Don_t you know your imperfections is a blessing => Kendrick Lamar");
        quotes.add("Reality is wrong, dreams are for real => Tupac");
        quotes.add("To live will be an awfully big adventure => Peter Pan");
        quotes.add("Try to be a rainbow in someone_s cloud => Maya Angelou");
        quotes.add("There is no substitute for hard work => Thomas Edison");
        quotes.add("What consumes your mind controls your life- Unknown");
        quotes.add("Strive for greatness => Lebron James");
        quotes.add("Wanting to be someone else is a waste of who you are => Kurt Cobain");
        quotes.add("And still, I rise => Maya Angelou");
        quotes.add("The time is always right to do what is right => Martin Luther King Jr.");
        quotes.add("Let the beauty of what you love be what you do => Rumi");
        quotes.add("May your choices reflect your hopes, not your fears => Nelson Mandela");
        quotes.add("A happy soul is the best shield for a cruel world => Atticus");
        quotes.add("White is not always light and black is not always dark => Habeeb Akande");
        quotes.add("Life becomes easier when you learn to accept the apology you never got => R. Brault");
        quotes.add("Happiness depends upon ourselves => Aristotle");
        quotes.add("Turn your wounds into wisdom => Oprah Winfrey");
        quotes.add("Change the game, don_t let the game change you => Macklemore");
        quotes.add("It hurt because it mattered => John Green");
        quotes.add("If the world was blind how many people would you impress? => Boonaa Mohammed");
        quotes.add("I will remember and recover, not forgive and forget => Unknown");
        quotes.add("The meaning of life is to give life meaning => Ken Hudgins");
        quotes.add("The true meaning of life is to plant trees, under whose shade you do not expect to sit => Nelson Henderson");
        quotes.add("When words fail, music speaks => Shakespeare");
        quotes.add("Embrace the glorious mess that you are => Elizabeth Gilbert");
        quotes.add("Normality is a paved road: it_s comfortable to walk but no flowers grow => Vincent van Gogh");
        quotes.add("I have nothing to lose but something to gain => Eminem");
    }

    void ShowQuote(int day, int month, byte rand[]) {
        int flag = 0;
        for (int i = 0; i < this.day.size(); i++) {
            if (this.day.get(i) == day && this.month.get(i) == month) {
                System.out.println("\n" + specialQuote.get(i));
                flag++;
            }

        }
        if (flag == 0) {
            // one method to check the frequency of occurance of the same quote and rectify it
            // not any special date
            rand[0] =(byte) (Math.abs(rand[0]));
            if (rand[0] == -128)
            {
                rand[0] = 50;
            }
            System.out.println("\n" + quotes.get(rand[0]));

        } else {
            flag = 0;
        }
    }
}
