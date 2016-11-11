package main.error_handler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;

public class SyntaxError extends BaseErrorListener {
    @Override
    public void syntaxError(@NotNull Recognizer<?, ?> recognizer, @Nullable Object offendingSymbol,
                            int line, int charPositionLine, @NotNull String msg,
                            @Nullable RecognitionException e) {
        System.err.println("line "+ line + ":" + charPositionLine + " : " +
                msg);
    }
}
