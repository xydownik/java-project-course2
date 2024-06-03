package Actors;


import Tools.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface DealerWithRequest.
 */
public interface DealerWithRequest {
    
    /**
     * See.
     *
     * @return the string
     */
    void seeAll();

    /**
     * Accept.
     *
     * @param r the r
     */
    void accept(Request r);

    /**
     * Reject.
     *
     * @param r the r
     */
    void reject(Request r);
}

