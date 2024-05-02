import axios from "axios";

const enrollmentFetch = axios.create({
    baseURL: "http://localhost:8082/enrollment",
    headers: {
        "Content-Type": "application/json",
    },
});

export default enrollmentFetch;