package core_algorithms;

public class MiniMax <S, A> {
    private Game<S,A> game;
    private final boolean pruning;

    public MiniMax(Game<S,A>, game, boolean pruning){
        this.game = game;
        this.pruning = pruning;
    }

    public A minimaxSearch(S state){
        Best<A> max = maxValue(state);
        return max.action();
    }
}
