import { task } from './task';

export interface epic {
    id: string,
    name: string
    task: Array<task>
}