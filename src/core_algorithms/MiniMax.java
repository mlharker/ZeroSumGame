package core_algorithms;

import problems.Game;

import java.util.List;

public class MiniMax <S, A> {
    public Game<S,A> game;

    public record Best<A>(int value, A action){};
    public final boolean pruning;

    public MiniMax(Game<S,A> game, boolean pruning)
    {
        this.game = game;
        this.pruning = pruning;
    }

    public A minimaxSearch(S state){
        Best<A> max = maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return max.action();
    }

    public Best<A> maxValue(S state, int alpha, int beta){
        int maxValue = Integer.MIN_VALUE;
        A maxAction = null;
        if(game.isTerminal(state)) {
            maxValue = game.utility(state);
        }else{
            for(A action : game.actions(state))
            {
                A test = (A) game.actions(state);
                S newState = game.execute(action, state);
                Best<A> min = minValue(newState, alpha, beta);
                if (min.value() > maxValue)
                {
                    maxValue = min.value();
                    maxAction = action;
                }
                game.undo(action, newState);

                if (pruning && maxValue >= beta)
                    break;
                alpha = Math.max(alpha, maxValue);
            }
        }
        return new Best<>(maxValue,maxAction);
    }

    public Best<A> minValue(S state, int alpha, int beta){
        int minValue = Integer.MAX_VALUE;
        A minAction = null;
        if(game.isTerminal(state))
        {
            minValue = game.utility(state);
        }
        else{
            for(A action : game.actions(state)){
                S newState = game.execute(action, state);
                Best<A> max = maxValue(newState, alpha, beta);
                if(max.value() < minValue){
                    minValue = max.value();
                    minAction = action;
                }
                game.undo(action, newState);

                if (pruning && minValue <= alpha)
                    break;
                beta = Math.min(beta, minValue);
            }
        }
        return new Best<>(minValue, minAction);
    }

    public Game<S, A> getGame()
    {
        return this.game;
    }

}
