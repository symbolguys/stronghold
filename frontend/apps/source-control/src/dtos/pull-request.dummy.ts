import { PullRequest } from './pull-request.data';

export class PullRequestDummyData {
  get() {
    return [
      new PullRequest(
        'ID 1',
        'Test 1',
        'master',
        'HIGH',
        'ACTIVE',
        'OPEN',
        new Date("2015-03-25T12:00:00Z"),
        new Date("2015-03-25T12:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 2',
        'Test 2',
        'featurebranch',
        'HIGH',
        'INACTIVE',
        'OPEN',
        new Date("2015-04-25T18:00:00Z"),
        new Date("2015-04-25T18:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 3',
        'Test 3',
        'featurebranch',
        'MEDIUM',
        'ACTIVE',
        'OPEN',
        new Date("2015-05-25T20:00:00Z"),
        new Date("2015-05-25T20:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 4',
        'Test 4',
        'master',
        'HIGH',
        'INACTIVE',
        'MERGED',
        new Date("2015-06-25T01:32:00Z"),
        new Date("2015-06-25T01:32:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 5',
        'Test 5',
        'dummybranch',
        'LOW',
        'INACTIVE',
        'DECLINED',
        new Date("2015-01-01T12:00:00Z"),
        new Date("2015-01-01T12:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 6',
        'Test 6',
        'dummybranch',
        'MEDIUM',
        'FAILED',
        'MERGED',
        new Date("2015-08-25T12:00:00Z"),
        new Date("2015-08-25T12:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
      new PullRequest(
        'ID 7',
        'Test 7',
        'master',
        'MEDIUM',
        'FAILED',
        'OPEN',
        new Date("2015-12-10T22:00:00Z"),
        new Date("2015-12-10T22:00:00Z"),
        new Date(),
        2,
        13,
        3,
        10
      ),
    ];
  }
}
