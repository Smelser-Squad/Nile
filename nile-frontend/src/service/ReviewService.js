import axios from 'axios'


export async function getReviews(productId){
    const reviews=await axios.get(`http://54.82.100.75/api/reviews/byproduct/${productId}`)
    const data=reviews.data;
    return data;

}

export async function getAverageReviewScore(productId) {
    const reviewData = {avg: 0, length: 0};
    const reviews=await axios.get(`http://54.82.100.75/api/reviews/byproduct/${productId}`)
        .catch(err => {
            return 0;
        });
    if (reviews === 0) {
        return reviewData;
    }
    const data=reviews.data;
    let sum = 0;
    for (let i = 0; i < data.length; i++) {
        sum += data[i].rating;
    }
    let avg = sum / data.length;
    reviewData["avg"] = avg.toFixed(1);
    reviewData["length"] = data.length;
    return reviewData;
}

export function addReview(Review,productId){
    axios.post(`http://54.82.100.75/api/reviews`,Review);
    window.location.replace(`http://tp-nile.s3-website.us-east-2.amazonaws.com/singleProductListing/${productId}`)
}

export function addFeatureRating(featureRating){
    axios.post(`http://54.82.100.75/api/featureRating`,featureRating)
}