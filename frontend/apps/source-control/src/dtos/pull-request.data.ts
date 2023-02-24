export class PullRequest {
  constructor(
    readonly uuid: string,
    readonly title: string,
    readonly destination: string,
    readonly quality: "HIGH" | "MEDIUM" | "LOW",
    readonly status: "ACTIVE" | "INACTIVE" | "FAILED",
    readonly state: "OPEN" | "MERGED" | "DECLINED",
    readonly createDate: Date,
    readonly updateDate: Date,
    readonly closedDate: Date,
    readonly commits: number,
    readonly fileChanges: number,
    readonly reviewers: number,
    readonly comments: number,
  ) {}
}
