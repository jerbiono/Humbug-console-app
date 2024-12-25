package g54685.humbug.model;

/**
 * Position of the animal on the board. A position contains rows and columns
 * that define the cord of the animal. A position can change according to
 * direction and the value of deltaRow and deltaColumn. Animals positions can be
 * compared thanks to its hashcode using the equals method. To display the
 * position we use the toString method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public  class Position {

    private final int row ;
    private final int column;
    
    /**
     * Default Constructor of position. 
     */
    public Position(){
        this.row = 0;
        this.column= 0;
    }
    /**
     * Constructor of position on board.
     *
     * @param row indicate the vertical direction.
     * @param column indicate the horizontale direction.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
   
    /**
     * Get the value of row
     *
     * @return the value of row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the value of col
     *
     * @return the value of col
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during an
     * execution of a Java application, the {@code hashCode} method must
     * consistently return the same integer, provided no information used in
     * {@code equals} comparisons on the object is modified. This integer need
     * not remain consistent from one execution of an application to another
     * execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of the two
     * objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal according
     * to the {@link java.lang.Object#equals(java.lang.Object)} method, then
     * calling the {@code hashCode} method on each of the two objects must
     * produce distinct integer results. However, the programmer should be aware
     * that producing distinct integer results for unequal objects may improve
     * the performance of hash tables.
     * </ul>
     *
     * @implSpec As far as is reasonably practical, the {@code hashCode} method
     * defined by class {@code Object} returns distinct integers for distinct
     * objects.
     *
     * @return a hash code value for this object.
     * @see java.lang.Object#equals(java.lang.Object)
     * @see java.lang.System#identityHashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.row;
        hash = 67 * hash + this.column;
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation on non-null
     * object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value {@code x},
     * {@code x.equals(x)} should return {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values {@code x}
     * and {@code y}, {@code x.equals(y)} should return {@code true} if and only
     * if {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values {@code x},
     * {@code y}, and {@code z}, if {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then {@code x.equals(z)} should
     * return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values {@code x}
     * and {@code y}, multiple invocations of {@code x.equals(y)} consistently
     * return {@code true} or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the objects is
     * modified.
     * <li>For any non-null reference value {@code x}, {@code x.equals(null)}
     * should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements the most
     * discriminating possible equivalence relation on objects; that is, for any
     * non-null reference values {@code x} and {@code y}, this method returns
     * {@code true} if and only if {@code x} and {@code y} refer to the same
     * object ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the general
     * contract for the {@code hashCode} method, which states that equal objects
     * must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

    /**
     * allows to return the position of the square, the return will be a string.
     *
     *
     * @return the position of the square.
     */
    @Override
    public String toString() {
        return "("+this.row+ ","+ this.column+")" ;
    }

    /**
     * allows to return the new position of the animal on the board. the new
     * position will be updated according to the type of animal. which depends
     * on change of deltarow and deltacolumn.
     *
     * @param direction the direction of the animal which will be choiced by the
     * player.
     * @return the new position of the animal.
     */
    public Position next(Direction direction) {
        return new Position(this.row + direction.getDeltaRow(), this.column + direction.getDeltaColumn());
    }
}
