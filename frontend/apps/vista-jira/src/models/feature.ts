import { epic } from "./epic"

export interface feature {
    id: string,
    name: string,
    epic: Array<epic>
}