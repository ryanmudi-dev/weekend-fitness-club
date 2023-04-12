package com.WFC.weekendFitnessClub;

/**

 The Rating class represents a rating given by a user, which includes a numerical score and an optional review.
 */

public class Rating {
    private String review;
    private int rating;

    /**
     * Constructs a Rating object with the given rating and review.
     *
     * @param rating the numerical score given by the user
     * @param review the optional review given by the user
     */
    public Rating (int rating, String review){
        this.rating = rating;
        this.review = review;
    }

    /**
     * Constructs a Rating object with the given rating and an empty review.
     *
     * @param rating the numerical score given by the user
     */
    public Rating (int rating){
        this.rating = rating;
        this.review = "";
    }

    /**
     * Returns the review associated with the rating.
     *
     * @return the review associated with the rating
     */
    public String getReview() {
        return review;
    }

    /**
     * Sets the review associated with the rating.
     *
     * @param review the review to be associated with the rating
     */
    public void setReview(String review) {
        this.review = review;
    }


    /**
     * Returns the numerical score associated with the rating.
     *
     * @return the numerical score associated with the rating
     */
    public int getRating() {
        return rating;
    }


    /**
     * Sets the numerical score associated with the rating.
     *
     * @param rating the numerical score to be associated with the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

}
