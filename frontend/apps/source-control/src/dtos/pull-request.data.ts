export class PullRequest {
  constructor(
    readonly uuid: string,
    readonly title: string,
    readonly quality: "HIGH" | "MEDIUM" | "LOW",
    readonly status: "ACTIVE" | "INACTIVE" | "FAILED" | "MERGED" | "DECLINED",
    readonly createDate: Date,
    readonly updateDate: Date,
    readonly closedDate: Date
  ) {}
}
