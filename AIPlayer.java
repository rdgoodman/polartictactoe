package polartictactoe;

import java.util.LinkedList;

public class AIPlayer implements Player {
	
	int maxSearchDepth;
	//TODO:the next two should be parameters reported by reportMove() and its delegates (minimax, etc)?
	int numNodesEvaluated;
	int timeToSelect;
	int playerNum;
	LinkedList<Edge> edges;
	Game game;
	
	public AIPlayer(int playerNum){
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
	}


	@Override
	public void chooseMove() {

	}

	@Override
	public void reportMove(long[] results) {

		
	}

	
	@Override
	public int getPlayerNum() {
		return playerNum;
	}

	@Override
	public LinkedList<Edge> getEdges() {
		return edges;
	}


	@Override
	public void addEdge(Edge newEdge) {
		edges.add(newEdge);
		
	}


	@Override
	public boolean hasEdge(Edge seeking) {
		if(edges.isEmpty()){
			return false;
		} else if (edges.contains(seeking)){
			return true;
		}
		return false;
	}


	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}
}
