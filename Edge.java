package rolliepolartictactoe;

public class Edge {
	private static final int RADIAL = 0;
	private static final int ARC = 1;
	private static final int SPIRALRIGHT = 2;
	private static final int SPIRALLEFT = 3;
	private static final int SPIRAL = 4;

	int type;
	int color; // shows what player the edge belongs to
	Node start;
	Node end;
	// order is arbitrary as to which is start and end
	// below here are strings used for building axioms
	String startString;
	String endString;
	String edgeType;
	String edgeName;

	/** 
	 * 
	 * @param start start node
	 * @param end end node
	 * @param color player number
	 * @param maxY number of lines in this game
	 */
	public Edge(Node start, Node end, int color, int maxY) {
		this.start = start;
		this.end = end;
		this.color = color;

		setType(maxY);

		startString = Integer.toString(this.start.getX())
				+ Integer.toString(this.start.getY());
		endString = Integer.toString(this.end.getX())
				+ Integer.toString(this.end.getY());
		edgeName = startString + "-" + endString;

		edgeType = "SR";
		if (type == RADIAL) {
			edgeType = "R";
		} else if (type == ARC) {
			edgeType = "A";
		} else if (type == SPIRALLEFT) {
			edgeType = "SL";
		}

	}

	/** Creates the axiom to add to the KB */
	public EdgeAxiom getEdgeAxiom() {
		EdgeAxiom axiom = new EdgeAxiom(startString, endString, edgeType,
				edgeName);
		return axiom;
	}


	/** Determines and assigns the type of this Edge (radial, arc, spiral) */
	private void setType(int maxY) {

		if (start.getX() == end.getX()) {
			type = ARC;
		} else if (start.getY() == end.getY()) {
			type = RADIAL;
		} else {
			type = SPIRAL;
		}

		setStartAndEnd(maxY);

	}

	/** Changes start/end order to keep consistency */
	private void setStartAndEnd(int maxY) {

		// for radial nodes, the node with the smaller x-value is the start
		if (type == RADIAL) {
			if (start.getX() < end.getX()) {
				// do nothing
			} else {
				Node holder = start;
				start = end;
				end = holder;
			}
		}

		// for arc nodes, the node with the smaller y-value is the
		// start
		if (type == ARC) {
			if (start.getY() < end.getY() || (start.getY() == maxY && end.getY() == 0)) {
				// do nothing
			} else {
				Node holder = start;
				start = end;
				end = holder;
			}
		}

		// for spiral nodes
		if (type == SPIRAL) {
			if ((start.getY() < end.getY())
					|| (start.getY() == maxY && end.getY() == 0)) {
				if (start.getX() < end.getX()) {
					type = SPIRALRIGHT;
				} else {
					type = SPIRALLEFT;
				}
			} else {
				Node holder = start;
				start = end;
				end = holder;
				if (start.getX() < end.getX()) {
					type = SPIRALRIGHT;
				} else {
					type = SPIRALLEFT;
				}
			}
		}

	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this.getClass() != o.getClass()) {
			return false;
		} else if ((this.start != ((Edge) o).getStart())
				|| (this.end != ((Edge) o).getEnd())) {
			return false;
		}

		return true;
	}

	public String toString() {
		return ("[" + start.toString() + "->" + end.toString() + ": " + type + "]");
	}

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public Node getStart() {
		return start;
	}

	public Node getEnd() {
		return end;
	}

}
