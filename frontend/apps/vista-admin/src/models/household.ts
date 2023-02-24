import { Member } from "./member";

export class Household {
    constructor(
        public name: string,
        public id: string,
        public members: Array<Member>
    ){
    }
}