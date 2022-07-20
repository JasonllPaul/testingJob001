const makeRequest = async (url) => {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    return await fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            return result;
        })
        .catch(error => console.log('::error', error));
}


const API = {
    async get(url) {
        return await makeRequest(url);
    }
};

export default API;
