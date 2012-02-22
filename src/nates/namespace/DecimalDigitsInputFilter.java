package nates.namespace;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Input filter that limits the number of decimal digits that are allowed to be
 * entered.
 */
public class DecimalDigitsInputFilter implements InputFilter {

  private final int decimalDigits;

  /**
   * Constructor.
   * 
   * @param decimalDigits maximum decimal digits
   */
  public DecimalDigitsInputFilter(int decimalDigits) {
    this.decimalDigits = decimalDigits;
  }

  public CharSequence filter(CharSequence source,
      int start,
      int end,
      Spanned dest,
      int dstart,
      int dend) {


    int dotPos = -1;
    int len = dest.length();
    for (int i = 0; i < len; i++) {
      char c = dest.charAt(i);
      if (c == '.' || c == ',') {
        dotPos = i;
        break;
      }
    }
    if (dotPos > 0) {
      // if the text is entered before the dot
      if (dend <= dotPos) {
        return null;
      }
      if (len - dotPos > decimalDigits) {
        return "";
      }
    }

    return null;
  }

}

