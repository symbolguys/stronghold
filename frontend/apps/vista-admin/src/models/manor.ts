import { Household } from "./household";

export class Manor {
    constructor(
        public name: string,
        public households: Array<Household>){
    }
}