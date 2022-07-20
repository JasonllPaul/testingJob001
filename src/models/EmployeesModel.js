import API from "../clients/WebClient";

export const get = async (url) => {
    try {
        const response = await API.get(url);
        return JSON.parse(response);
    } catch (e) {
        return 429;
    }
}