export class DefectIssue {
  id: string;
  key: string;
  summary: string;
  description: string;
  status: string;
  priority: string;
  assignee: string;
  reporter: string;
  created: string;
  updated: string;
  comments: Comment[];
  severity: string;

  constructor(data: any) {
    this.id = data.id;
    this.key = data.key;
    this.summary = data.fields.summary;
    this.description = data.fields.description;
    this.status = data.fields.status.name;
    this.priority = data.fields.priority.name;
    this.assignee = data.fields.assignee ? data.fields.assignee.displayName : 'Unassigned';
    this.reporter = data.fields.reporter.displayName;
    this.created = data.fields.created;
    this.updated = data.fields.updated;
    this.comments = data.fields.comment.comments.map((commentData: any) => new Comment(commentData));
    this.severity = data.fields.customfield_10000; // Replace 'customfield_10000' with the ID of the severity field in your JIRA instance
  }
}

export class Comment {
  id: string;
  author: string;
  body: string;
  created: string;

  constructor(data: any) {
    this.id = data.id;
    this.author = data.author.displayName;
    this.body = data.body;
    this.created = data.created;
  }
}