package Game4;

import java.awt.*;
import java.util.ArrayList;

public class DataOfSquare {

    // قائمة الألوان المستخدمة لحالات المربعات
    private final ArrayList<Color> colorStates = new ArrayList<>();

    // تمثل نوع المحتوى داخل المربع (0 = فارغ، 1 = طعام، 2 = ثعبان)
    private int colorIndex;

    // المربع الرسومي الذي يتم عرضه في الواجهة
    public final SquarePanel square;

    // المُنشئ: يضبط اللون الابتدائي للمربع
    public DataOfSquare(int initialColorIndex) {
        initializeColors();
        this.colorIndex = initialColorIndex;
        this.square = new SquarePanel(colorStates.get(colorIndex));
    }

    // تغيّر لون المربع حسب الحالة الجديدة
    public void lightMeUp(int newColorIndex) {
        if (newColorIndex >= 0 && newColorIndex < colorStates.size()) {
            square.ChangeColor(colorStates.get(newColorIndex));
        }
    }
    

    // إعداد قائمة الألوان الثابتة المستخدمة في اللعبة
    private void initializeColors() {
        colorStates.add(Color.DARK_GRAY); // 0 - فارغ
        colorStates.add(Color.BLUE);      // 1 - طعام
        colorStates.add(Color.WHITE);     // 2 - ثعبان
    }
}
