import axios from "axios";

const libraryFetch = axios.create({
    baseURL: "http://localhost:8084/library",
    headers: {
        "Content-Type": "application/json",
    },
});

export default libraryFetch;